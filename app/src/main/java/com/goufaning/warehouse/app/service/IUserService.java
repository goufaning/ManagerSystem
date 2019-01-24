package com.goufaning.warehouse.app.service;


import com.goufaning.warehouse.app.entity.User;

public interface IUserService {

    void create(String name, String password, int permissions, String description);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    User getUserByNameAndPassword(String name, String password);
}
