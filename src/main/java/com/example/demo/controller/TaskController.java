package com.example.demo.controller;

import com.example.demo.dto.task.TaskCreateRequest;
import com.example.demo.dto.task.TaskResponse;
import com.example.demo.dto.task.TaskWithCustomerResponse;
import com.example.demo.dto.task.TaskUpdateRequest;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(
            @RequestBody TaskCreateRequest request) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest request
    ) {
        TaskResponse taskUpdate = taskService.updateTask(id, request);
        return ResponseEntity.ok(taskUpdate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskId(
            @PathVariable Long id
    ) {
        TaskResponse response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> response = taskService.getAllTasks();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<TaskWithCustomerResponse>> getAllTasksFetch() {
        return ResponseEntity.ok(taskService.getAllTasksFetch());
    }

    @GetMapping("/graph")
    public ResponseEntity<List<TaskWithCustomerResponse>> getAllTasksGraph() {
        return ResponseEntity.ok(taskService.getAllTasksGraph());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(
            @PathVariable Long id
    ) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}
