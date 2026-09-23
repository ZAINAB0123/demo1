package com.example.demo.dto.user;

public record LoginResponse(
       String accessToken,
       Long expiresIn
) {
}
