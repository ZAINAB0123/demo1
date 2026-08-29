package com.example.demo.repository.task;

import com.example.demo.dto.task.TaskListResponse;
import com.example.demo.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepository {
    private final TaskJpaRepository repository;

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Task task) {
        repository.delete(task);
    }

    @Override
    public List<Task> findAllWithCustomerFetch() {
        return repository.findAllWithCustomerFetch();
    }

    @Override
    public List<Task> findAllWithTaskGraph() {
        return repository.findAllWithTaskGraph();
    }

    @Override
    public Page<TaskListResponse> getTaskListPage(Pageable pageable) {
        return repository.getTaskListPage(pageable);
    }
}
