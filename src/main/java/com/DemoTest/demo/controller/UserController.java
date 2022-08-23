package com.DemoTest.demo.controller;

import com.DemoTest.demo.exception.RequiredFieldValidationException;
import com.DemoTest.demo.exception.UserNotFoundException;
import com.DemoTest.demo.model.User;
import com.DemoTest.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {

        if (user == null ||
                user.getUserId() <= 0 ||
                user.getUserFirstName() == null ||
                user.getUserFirstName().isEmpty() ||
                user.getUserLastName() == null || user.getUserLastName().isEmpty()) {

            throw new RequiredFieldValidationException();
        }

        userService.createUser(user);

        return new ResponseEntity<User>(user, HttpStatus.CREATED);

    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<User> getUserById(@RequestParam int userId) {

        if (userId <= 0)
            throw new RequiredFieldValidationException();

        User filterUser = userService.findUser(userId);

        if (filterUser == null)
            throw new UserNotFoundException("User not found for the given user id ".concat(" ").concat(String.valueOf(userId)));

        return new ResponseEntity<User>(filterUser, HttpStatus.FOUND);
    }
//
//    @PutMapping("/users/{user_id}")
//    @ResponseBody
//    public User update(@PathVariable("user_id") Long userId, @RequestBody User userObject)
//    {
//        User user = userRepository.findOne(userId);
//        user.setName(userObject.getName());
//        user.setCountry(userObject.getCountry());
//        return userRepository.save(user);
//    }

}
