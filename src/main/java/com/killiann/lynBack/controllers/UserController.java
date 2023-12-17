package com.killiann.lynBack.controllers;

import com.killiann.lynBack.exceptions.UserNotFoundException;
import com.killiann.lynBack.models.User;
import com.killiann.lynBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{user_id}")
    public User one(@PathVariable Long user_id) {
        return userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException(user_id));
    }
}