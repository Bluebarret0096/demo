package com.DemoTest.demo.repository;

import com.DemoTest.demo.model.User;

public interface UserRepository {

    User createUser(User user);

    User findUser(int userId);
}
