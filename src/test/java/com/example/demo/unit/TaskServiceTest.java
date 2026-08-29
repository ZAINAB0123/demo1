package com.example.demo.unit;

import com.example.demo.dto.task.*;
import com.example.demo.entity.Task;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.repository.task.TaskRepository;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_shouldBeCreateAndReturnTask() {
        TaskCreateRequest request = mock(TaskCreateRequest.class);
        Task task = new Task();
        Task savedTask = new Task();
        TaskResponse response = mock(TaskResponse.class);
        when(taskMapper.toEntity(request)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(savedTask);
        when(taskMapper.toResponse(savedTask)).thenReturn(response);
        TaskResponse result = taskService.createTask(request);
        assertNotNull(result);
        assertSame(response, result);
    }

    @Test
    void updateTask_shouldBeUpdateAndReturnTask() {
        Long id = 6L;
        TaskUpdateRequest updateRequest = mock(TaskUpdateRequest.class);
        Task task = new Task();
        TaskResponse response = mock(TaskResponse.class);
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.toResponse(task)).thenReturn(response);
        when(updateRequest.description()).thenReturn("new description");
        when(updateRequest.title()).thenReturn("new  title");
        TaskResponse result = taskService.updateTask(id, updateRequest);
        assertNotNull(result);
        assertSame(response, result);
        assertEquals("new description", task.getDescription());
        assertEquals("new  title", task.getTitle());
    }

    @Test
    void getTaskById_shouldReturnTaskResponse() {
        Long id = 5L;
        Task task = new Task();
        TaskResponse response = mock(TaskResponse.class);
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.toResponse(task)).thenReturn(response);
        TaskResponse result = taskService.getTaskById(id);
        assertNotNull(result);
        assertSame(response, result);

    }

    @Test
    void getTaskById_shouldThrowTaskNotFoundException() {
        Long id = 5L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(id));
    }

    @Test
    void getAllTasks_shouldReturnTask_shouldReturnTask() {
        List<Task> tasks = new ArrayList<>();
        List<TaskResponse> taskResponses = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasks);
        when(taskMapper.toResponseList(tasks)).thenReturn(taskResponses);
        List<TaskResponse> result = taskService.getAllTasks();
        assertNotNull(result);
        assertSame(taskResponses, result);
    }

    @Test
    void getAllTasksFetch_shouldReturnTasks() {
        List<Task> tasks = new ArrayList<>();
        List<TaskWithCustomerResponse> taskList = new ArrayList<>();
        when(taskRepository.findAllWithCustomerFetch()).thenReturn(tasks);
        when(taskMapper.toResponseList2(tasks)).thenReturn(taskList);
        List<TaskWithCustomerResponse> result = taskService.getAllTasksFetch();
        assertNotNull(result);
        assertSame(taskList, result);
    }

    @Test
    void getAllTasksGraph_shouldReturnTasks() {
        List<Task> tasks = new ArrayList<>();
        List<TaskWithCustomerResponse> taskList = new ArrayList<>();
        when(taskRepository.findAllWithTaskGraph()).thenReturn(tasks);
        when(taskMapper.toResponseList2(tasks)).thenReturn(taskList);
        List<TaskWithCustomerResponse> result = taskService.getAllTasksGraph();
        assertNotNull(result);
        assertSame(taskList, result);
    }

    @Test
    void taskListResponsePage_shouldReturnTasks() {
        Pageable pageable = mock(Pageable.class);
        Page<TaskListResponse> taskPage = mock(Page.class);
        when(taskRepository.getTaskListPage(pageable)).thenReturn(taskPage);
        Page<TaskListResponse> result = taskService.taskListResponsePage(pageable);
        assertNotNull(result);
        assertSame(taskPage, result);
    }
}
