package com.example.demo.dto.task;
import com.example.demo.entity.task.TaskStatus;

public record TaskFilterRequest(
        String title,
        TaskStatus status,
        Integer minDeals,
        Integer maxDeals
) {
}
