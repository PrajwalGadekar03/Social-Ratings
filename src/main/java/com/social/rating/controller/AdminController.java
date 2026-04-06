package com.social.rating.controller;

import com.social.rating.entity.User;
import com.social.rating.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam UUID userId) {
        adminService.deleteUser(userId);
        return "User deleted";
    }

    @PostMapping("/block-user")
    public User blockUser(@RequestParam UUID userId) {
        return adminService.blockUser(userId);
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return adminService.getDashboardStats();
    }
}