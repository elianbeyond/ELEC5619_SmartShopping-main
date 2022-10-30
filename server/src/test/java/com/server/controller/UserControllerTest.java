package com.server.controller;

import com.server.entity.Product;
import com.server.entity.User;
import com.server.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class UserControllerTest {

    @Autowired
    UserMapper userMapper;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getName() {
        String name = userMapper.findUserById(8).getUsername();
        System.out.println(name);
    }

    @Test
    void updateInfo() {
        User originalUser = userMapper.findUserByUsername("guo");
        System.out.println("Original user is" + originalUser);
        originalUser.setUsername("jjj");
        originalUser.setUserEmail("312@gmail.com");
        originalUser.setAddress("62 blamey st");
        String password = "321";
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        originalUser.setPassword(password);
        userMapper.save(originalUser);
        User finalUser = userMapper.findUserByUsername("jjj");
        System.out.println("Final user is "+ finalUser);
    }

    @Test
    void getUser() {
        String username = "guo";
        User user = userMapper.findUserByUsername(username);
        assert user!= null : "test failed";
        System.out.println(user);
    }
}