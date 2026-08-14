package com.example.demo.dto.task;
import com.example.demo.entity.TaskStatus;

public record TaskFilterRequest(
        String title,
        TaskStatus status,
        Integer minDeals,
        Integer maxDeals
) {
}
