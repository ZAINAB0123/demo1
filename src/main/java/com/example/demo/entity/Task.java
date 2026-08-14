package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task extends BaseEntity{
    @Column(nullable = false, length = 255)
    private String title;
    @Lob
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
public Task(String title, String description){
    this.title = title;
    this.description = description;
    this.status = TaskStatus.NEW;
}


}
