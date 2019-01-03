package com.goufaning.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void create(String name, String password, int permissions, String description) {
        jdbcTemplate.update("insert into user_332(name,password,permissions,description) values(?,?,?,?)", name, password, permissions, description);
    }

    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from user_332", Integer.class);
    }
}
