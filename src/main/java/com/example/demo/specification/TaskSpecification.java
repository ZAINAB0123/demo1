package com.example.demo.specification;

import com.example.demo.dto.task.TaskFilterRequest;
import com.example.demo.entity.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> getSpecification(TaskFilterRequest request) {
        if (request.status() != null) {
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), request.status()));
        } else {
            return null;   //Specification.where(null);
        }
    }
}
