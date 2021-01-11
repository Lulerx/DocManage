package com.bazn.document.utils;

import com.bazn.document.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by sang on 2017/12/20.
 */
public class Util {
    /**
     * 
     * 获取认证后未过期的用户 
     * @author itle 
     * @date 2020/11/18 18:03 
     * @return com.bazn.document.bean.User
     */
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
