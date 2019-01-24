package com.mini.dao;

import com.mini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hht
 * @create 2019-01-19 17:38
 */
public interface UserDao extends JpaRepository<User,Integer> {

    //findByName
    public User findByName(String name);

    //findByNameAndPassword
     public User getByNameAndPassword(String username,String password);
}
