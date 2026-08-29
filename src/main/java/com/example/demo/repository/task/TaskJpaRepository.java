package com.example.demo.repository.task;

import com.example.demo.dto.task.TaskListResponse;
import com.example.demo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    @Query("""
            SELECT t
            from Task  t
            join fetch t.customer
            """)
    List<Task> findAllWithCustomerFetch();

    @EntityGraph(attributePaths = "customer")
    @Query("select t from Task t")
    List<Task> findAllWithTaskGraph();

    @Query("""
            select new com.example.demo.dto.task.TaskListResponse(
            t.title,
            c.name
            )
            from Task t
            join t.customer c
            """)
    Page<TaskListResponse> getTaskListPage(Pageable pageable);

}
