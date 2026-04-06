package com.social.rating.controller;

import com.social.rating.dto.LoginRequest;
import com.social.rating.dto.LoginResponse;
import com.social.rating.dto.UpdateProfileRequest;
import com.social.rating.entity.User;
import com.social.rating.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {


    private final UserService userService;
    private final com.social.rating.security.JwtUtil jwtUtil;

    public UserController(UserService userService,  com.social.rating.security.JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request) {

        User user = userService.loginUser(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }

    @GetMapping("/me")
    public User getMyProfile() {
        return userService.getLoggedInUser();
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }

    @PutMapping("/update-profile")
    public User updateProfile(@RequestBody UpdateProfileRequest request) {
        return userService.updateProfile(request);
    }

}