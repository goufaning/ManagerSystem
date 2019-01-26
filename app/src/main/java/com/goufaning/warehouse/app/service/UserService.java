package com.goufaning.warehouse.app.service;

import com.goufaning.warehouse.app.entity.User;
import com.goufaning.warehouse.app.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;


    public void create(String name, String password, int permissions, String description) {
        userMapper.insert(name, password, permissions, description);
    }

    public Integer getAllUsers() {
        return userMapper.getAll().size();
    }

    public User getUserByNameAndPassword(String name, String password) {
        return userMapper.findUserByNameAndPassword(name, password);
    }
}
