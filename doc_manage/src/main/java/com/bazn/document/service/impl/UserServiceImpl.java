package com.bazn.document.service.impl;

import com.bazn.document.bean.Role;
import com.bazn.document.bean.User;
import com.bazn.document.config.MyPasswordEncoder;
import com.bazn.document.mapper.RoleMapper;
import com.bazn.document.mapper.UserMapper;
import com.bazn.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/18
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUserName(username);
        if (user == null){
            //避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            return new User();
        }
        List<Role> roles = roleMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    @Override
    public int addUser(User user) {
        user.setPassword(myPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        try {
            return userMapper.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateUser(User user) {
        try {
            return userMapper.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User findUserById(Long id) {
        try {
            return userMapper.findUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
