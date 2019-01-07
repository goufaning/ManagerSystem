package com.goufaning.system.service;

import com.goufaning.system.user.entity.User;
import com.goufaning.system.dao.UserDao;

import java.util.List;

/**
 * Created by gfn on 2017-01-01.
 */
public class UserService {
    private UserDao userDao = new UserDao();
    public boolean register(User user) {
        return userDao.insert(user);
    }
    public User login(String username,String password) {
        return userDao.find(username,password);
    }
    public List<User> getAllUser() {
        return userDao.findAll();
    }
}
