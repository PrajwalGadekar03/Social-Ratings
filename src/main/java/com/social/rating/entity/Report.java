package com.social.rating.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID reportedByUserId;
    private UUID reportedUserId;
    private String reason;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}