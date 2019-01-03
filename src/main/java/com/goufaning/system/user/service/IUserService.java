package com.goufaning.system.user.service;

public interface IUserService {

    void create(String name, String password, int permissions, String description);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();
}
