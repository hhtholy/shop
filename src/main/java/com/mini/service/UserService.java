package com.mini.service;

import com.mini.entity.User;
import com.mini.util.Page;

/**
 * @author hht
 * @create 2019-01-19 17:35
 */
public interface UserService {

    //展示用户列表  分页的方式
    public Page<User> getUserPage(Integer currentPage,Integer size,int navigateNum);

    //判断用户是不是已经存在
    public boolean isExist(User user);

    //添加用户

    public void addUser(User user);

    //用户登录
    public User login(User user);
}
