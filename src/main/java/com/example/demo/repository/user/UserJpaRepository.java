package com.example.demo.repository.user;

import com.example.demo.entity.task.Task;
import com.example.demo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("""
select distinct user
from User user
left join fetch user.deals
""")
List<User> findAllWithDeals();
Optional<User> findByName(String name);
Optional<User> findByEmail(String email);
}
