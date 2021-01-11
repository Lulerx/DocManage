package com.bazn.document.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/18
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    /*
     * 参数CharSequence为用户输入的密码
     * 参数encodedPassword为调用 encode(CharSequence charSequence) 编码过后保存在数据库的密码
     */
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
    }
}
