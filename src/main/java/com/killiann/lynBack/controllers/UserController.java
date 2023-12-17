package com.killiann.lynBack.controllers;

import com.killiann.lynBack.exceptions.UserNotFoundException;
import com.killiann.lynBack.jwt.UserDetailsImpl;
import com.killiann.lynBack.models.User;
import com.killiann.lynBack.payloads.LoginRequest;
import com.killiann.lynBack.payloads.LoginResponse;
import com.killiann.lynBack.payloads.SignupRequest;
import com.killiann.lynBack.payloads.SignupResponse;
import com.killiann.lynBack.payloads.errors.GenericError;
import com.killiann.lynBack.repositories.UserRepository;
import com.killiann.lynBack.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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