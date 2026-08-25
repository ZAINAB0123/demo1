package com.example.demo.mapper;

import com.example.demo.dto.task.TaskCreateRequest;
import com.example.demo.dto.task.TaskResponse;
import com.example.demo.dto.task.TaskWithCustomerResponse;
import com.example.demo.entity.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {
    public Task toEntity(TaskCreateRequest request) {
      return new Task(
              request.title(),
              request.description()
      );
    }
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getVersion()
        );
    }
    public List<TaskResponse> toResponseList(List<Task> tasks) {
        List<TaskResponse> responseList = new ArrayList<>();
        for (Task task : tasks) {
            responseList.add(toResponse(task));
        }
        return responseList;
    }
    public TaskWithCustomerResponse toResponse2(Task task) {
        return new TaskWithCustomerResponse(
                task.getId(),
                task.getTitle(),
                task.getCustomer().getName()
        );
    }
    public List<TaskWithCustomerResponse> toResponseList2(List<Task> tasks) {
        List<TaskWithCustomerResponse> responseList = new ArrayList<>();
        for (Task task: tasks){
            responseList.add(toResponse2(task));
        }
        return responseList;
    }
}
