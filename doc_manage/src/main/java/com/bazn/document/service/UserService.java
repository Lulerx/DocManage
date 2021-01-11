package com.bazn.document.service;

import com.bazn.document.bean.User;

public interface UserService {

    int addUser(User user);

    int updateUser(User user);

    User findUserById(Long id);
}
