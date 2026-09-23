package com.example.demo.dto.user;
import com.example.demo.entity.user.UserRole;
import com.example.demo.entity.user.UserStatus;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        UserStatus status,
        UserRole role,
        LocalDateTime createdAt,
        Long version
) {
}
