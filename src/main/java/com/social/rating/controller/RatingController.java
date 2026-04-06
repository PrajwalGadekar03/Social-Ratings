package com.social.rating.controller;

import com.social.rating.entity.Rating;
import com.social.rating.service.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rate")
    public Rating rateUser(@RequestParam UUID userId,
                           @RequestParam Integer rating,
                           @RequestParam String comment) {

        return ratingService.rateUser(userId, rating, comment);
    }

    @GetMapping("/top-rated")
    public List<Object[]> getTopRatedUsers() {
        return ratingService.getTopRatedUsers();
    }

    @GetMapping("/leaderboard")
    public List<Object[]> getLeaderboard() {
        return ratingService.getLeaderboard();
    }
}