package com.social.rating.repository;

import com.social.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByRatedByUserIdAndRatedToUserId(UUID ratedByUserId, UUID ratedToUserId);


    @Query("""
SELECT r.ratedToUserId, AVG(r.overallRating)
FROM Rating r
GROUP BY r.ratedToUserId
ORDER BY AVG(r.overallRating) DESC
""")
    List<Object[]> findTopRatedUsers();

    @Query("""
SELECT r.ratedToUserId,
       AVG(r.overallRating),
       COUNT(r.id)
FROM Rating r
GROUP BY r.ratedToUserId
ORDER BY AVG(r.overallRating) DESC, COUNT(r.id) DESC
""")
    List<Object[]> getLeaderboard();
}