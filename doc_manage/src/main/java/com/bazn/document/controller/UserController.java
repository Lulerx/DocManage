package com.bazn.document.controller;

import com.bazn.document.bean.User;
import com.bazn.document.dto.Result;
import com.bazn.document.service.UserService;
import com.bazn.document.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUserMessage")
    public Result<User> getUserMessage() {
        Result<User> data = new Result();
        User currentUser = Util.getCurrentUser();
        currentUser.setPassword("");
        currentUser.setRegTime(null);
        data.setData(currentUser);
        return data;
    }


    @PostMapping("/register")
    public Result regUser(@RequestBody User user) {
        Result data = new Result();
        user.setRegTime(new Date());
        int state = userService.addUser(user);
        if (state > 0) {
            data.setMsg("注册成功");
        } else {
            data.setMsg("注册失败，请稍后重试");
        }
        return data;
    }


    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        Result data = new Result();
        int state = userService.updateUser(user);
        if (state > 0) {
            data.setMsg("信息已更新");
        } else {
            data.setMsg("更新失败，请重试");
        }

        return data;
    }

    @GetMapping("/getUserById")
    public Result<String> getUserById(@RequestParam("id") Long id){
        Result<String> data = new Result();
        User user = userService.findUserById(id);
        if (user != null){
            data.setData(user.getNickname());
        }
        return data;
    }

}
