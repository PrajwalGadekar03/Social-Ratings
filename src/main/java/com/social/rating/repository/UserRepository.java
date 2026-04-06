package com.social.rating.repository;

import com.social.rating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("""
SELECT u FROM User u
WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(u.city) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<User> searchUsers(String keyword);

    Optional<User> findByPhone(String phone);
}