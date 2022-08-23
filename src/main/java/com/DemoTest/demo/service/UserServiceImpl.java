package com.DemoTest.demo.service;

import com.DemoTest.demo.model.User;
import com.DemoTest.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository inMemoryRepository;

    @Override
    public User createUser(User user) {
        return inMemoryRepository.createUser(user);
    }

    @Override
    public User findUser(int userId) {
        return inMemoryRepository.findUser(userId);
    }


    private User mapper(int userId,
                     String userFirstName,
                     String userLastName
    ) {

        User user = new User();
        user.setUserId(userId);
        user.setUserFirstName(userFirstName);
        user.setUserLastName(userLastName);
        return user;
    }
}
