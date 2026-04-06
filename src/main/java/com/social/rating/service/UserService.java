package com.social.rating.service;

import com.social.rating.dto.UpdateProfileRequest;
import com.social.rating.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.social.rating.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setStatus("ACTIVE");
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Entered Password: " + password);
        System.out.println("DB Password: " + user.getPassword());

        boolean matches = passwordEncoder.matches(password, user.getPassword());
        System.out.println("Password Matches: " + matches);

        if (!matches) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public User getLoggedInUser() {
        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    public User updateProfile(UpdateProfileRequest request) {

        User user = getLoggedInUser();

        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            user.setLastName(request.getLastName());

        if (request.getBio() != null)
            user.setBio(request.getBio());

        if (request.getCity() != null)
            user.setCity(request.getCity());

        if (request.getProfilePictureUrl() != null)
            user.setProfilePictureUrl(request.getProfilePictureUrl());

        if (request.getPhone() != null)
            user.setPhone(request.getPhone());

        return userRepository.save(user);
    }
}