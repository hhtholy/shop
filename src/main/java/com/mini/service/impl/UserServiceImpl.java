package com.mini.service.impl;

import com.mini.dao.UserDao;
import com.mini.entity.User;
import com.mini.service.UserService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author hht
 * @create 2019-01-19 17:37
 */
@Service
public class UserServiceImpl implements UserService {


    //注入Dao

    @Autowired
    private UserDao userDao;

    /**
     * 获取用户列表
     * @param currentPage
     * @param size
     * @param navigateNum
     * @return
     */
    @Override
    public Page<User> getUserPage(Integer currentPage, Integer size, int navigateNum) {

        //设置分页信息

        Pageable pageable = new PageRequest(currentPage,size, Sort.Direction.DESC,"id");

        org.springframework.data.domain.Page<User> results = userDao.findAll(pageable);

        return new Page<>(results,navigateNum);

    }

    /**
     * 判断用户是不是已经存在
     * @param user
     * @return
     */
    @Override
    public boolean isExist(User user) {

        //获取用户名
        String name = user.getName();
        User result = userDao.findByName(name);
        return result==null?false:true;
    }

    /**
     * 添加用户
     * @param user
     */

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        User result = userDao.getByNameAndPassword(user.getName(), user.getPassword());
        return result;
    }
}
