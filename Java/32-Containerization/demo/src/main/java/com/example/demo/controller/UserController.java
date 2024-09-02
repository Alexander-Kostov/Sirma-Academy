package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping(path = "test")
    public String test() {
        return "Hello I am working fine";
    }
    @GetMapping(path = "users")
    public List<User> getUsers() {
        return  userRepository.findAll();
    }

    @PostMapping(path = "users")
    public User saveUser(@RequestBody User user) {
        return  userRepository.save(user);
    }
}
