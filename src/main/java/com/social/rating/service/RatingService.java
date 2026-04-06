package com.social.rating.service;

import com.social.rating.entity.Rating;
import com.social.rating.entity.User;
import com.social.rating.repository.RatingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;

    public RatingService(RatingRepository ratingRepository, UserService userService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    public Rating rateUser(UUID ratedToUserId, Integer ratingValue, String comment) {

        User loggedInUser = userService.getLoggedInUser();

        UUID ratedByUserId = loggedInUser.getId();

        Rating rating = ratingRepository
                .findByRatedByUserIdAndRatedToUserId(ratedByUserId, ratedToUserId)
                .orElse(new Rating());

        rating.setRatedByUserId(ratedByUserId);
        rating.setRatedToUserId(ratedToUserId);
        rating.setOverallRating(ratingValue);
        rating.setComment(comment);
        rating.setUpdatedAt(LocalDateTime.now());

        if (rating.getCreatedAt() == null) {
            rating.setCreatedAt(LocalDateTime.now());
        }

        return ratingRepository.save(rating);
    }

    public List<Object[]> getTopRatedUsers() {
        return ratingRepository.findTopRatedUsers();
    }
    public List<Object[]> getLeaderboard() {
        return ratingRepository.getLeaderboard();
    }
}