package com.goufaning.system;

import com.goufaning.system.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private IUserService userService;

    @Test
    public void test() {
        // 插入5个用户
        userService.create("a", "a", 1, "a");
        userService.create("b", "b", 1, "b");
        userService.create("c", "c", 1, "c");
        userService.create("d", "d", 1, "d");

        // 查数据库，应该有5个用户
        Assert.assertEquals(5, userService.getAllUsers().intValue());

    }

}