package com.example.demo.entity.user;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.deal.Deal;
import com.example.demo.entity.task.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 20)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Deal> deals = new ArrayList<>();
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = UserRole.USER;
        this.status = UserStatus.ACTIVE;

    }

    public void addDeal(Deal deal) {
        this.deals.add(deal);
        deal.setUser(this);

    }

    public void removeDeal(Deal deal) {
        this.deals.remove(deal);
        deal.setUser(null);
    }
    public void addTask(Task task) {
        this.tasks.add(task);
        task.setUser(this);

    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setUser(null);
    }
}
