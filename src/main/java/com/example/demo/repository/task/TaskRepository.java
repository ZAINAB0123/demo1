package com.example.demo.repository.task;
import com.example.demo.dto.task.TaskListResponse;
import com.example.demo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TaskRepository  {
    List<Task> findAll();
    Task save(Task task);
    Optional<Task> findById(Long id);
    void delete(Task task);
    List<Task> findAllWithCustomerFetch();
    List<Task> findAllWithTaskGraph();
    Page<TaskListResponse> getTaskListPage(Pageable pageable);

}
