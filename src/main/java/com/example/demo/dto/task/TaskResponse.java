package com.example.demo.dto.task;

import com.example.demo.entity.task.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        Long version

) {

}
