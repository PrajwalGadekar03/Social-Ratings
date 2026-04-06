package com.social.rating.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"rated_by_user_id", "rated_to_user_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rated_by_user_id")
    private java.util.UUID ratedByUserId;

    @Column(name = "rated_to_user_id")
    private java.util.UUID ratedToUserId;

    private Integer overallRating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}