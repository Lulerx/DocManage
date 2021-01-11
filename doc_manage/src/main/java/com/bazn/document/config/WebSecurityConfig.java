package com.bazn.document.config;

import com.bazn.document.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/18
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider())
                .httpBasic()
                //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 403);
                    map.put("message", "未登录");
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //请求认证管理
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("超级管理员")
                .anyRequest().authenticated() //必须授权才能范围 //其他的路径都是登录后即可访问

                //from表单登录设置
                .and()
                .formLogin()
                .loginProcessingUrl("/login")   //自定义处理认证的url    默认为/login
                .usernameParameter("username")  //设置form表单中用户名对应的name参数  默认为username
                .passwordParameter("password")  //设置form表单中密码对应的name参数  默认为password
                .permitAll()        //对于需要所有用户都可以访问的界面 或者url进行设置
                //登录失败，返回json
                .failureHandler((request, response, ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", 401);
                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                        map.put("message", "用户名或密码不正确");
                    } else if (ex instanceof DisabledException) {
                        map.put("message", "该账户不存在或被禁用");
                    } else {
                        map.put("message", "登录失败!");
                    }
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //登录成功，返回json
                .successHandler((request, response, authentication) -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", 200);
                    map.put("message", "登录成功");
                    map.put("data", authentication);
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                });

        //没有权限，返回json
        http.exceptionHandling()
                .accessDeniedHandler((request, response, ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", 403);
                    map.put("message", "权限不足，请联系超级管理员");
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                });

        //退出成功，返回json
        http.logout().permitAll();
        //开启跨域访问
        http.cors()
                .and()
                //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
                .csrf().disable();

    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/login/**");
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(userService);
        //对默认的PasswordEncoder进行覆盖
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }
}
