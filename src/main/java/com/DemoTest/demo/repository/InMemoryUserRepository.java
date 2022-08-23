package com.DemoTest.demo.repository;

import com.DemoTest.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final ConcurrentHashMap<Integer, User> inMemoryUser;

    public InMemoryUserRepository() {
        this.inMemoryUser = new ConcurrentHashMap<>();
    }

    @Override
    public User createUser(User user) {
        inMemoryUser.put(user.getUserId(), user);

        return user;
    }

    @Override
    public User findUser(int userId) {
        return inMemoryUser.get(userId);
    }
}
