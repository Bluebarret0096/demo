package com.DemoTest.demo.service;

import com.DemoTest.demo.model.User;

public interface UserService {

    User createUser(User user);
    User findUser(int userId);
}
