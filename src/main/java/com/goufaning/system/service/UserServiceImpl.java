package com.goufaning.system.service;

import com.goufaning.system.entity.User;
import com.goufaning.system.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;


    @Override
    public void create(String name, String password, int permissions, String description) {
        userMapper.insert(name, password, permissions, description);
    }

    @Override
    public Integer getAllUsers() {
        return userMapper.getAll().size();
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        return userMapper.findUserByNameAndPassword(name, password);
    }
}
