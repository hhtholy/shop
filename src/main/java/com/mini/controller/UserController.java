package com.mini.controller;

import com.mini.entity.User;
import com.mini.service.UserService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hht
 * @create 2019-01-19 17:42
 */
@RestController
public class UserController {

    //注入service
    @Autowired
    private UserService userService;

    /**
     * 分页查询用户信息
     * @param currentPage
     * @param size
     * @return
     */
    @GetMapping("/users")
    public Page<User> getUsers(@RequestParam(value = "currentPage", defaultValue="0") Integer currentPage,
                               @RequestParam(value = "size", defaultValue="4") Integer size){


        return userService.getUserPage(currentPage,size,5);

    }
}
