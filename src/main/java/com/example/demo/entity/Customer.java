package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 20)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerStatus status;
    @OneToMany(mappedBy = "customer")
    private List<Task> tasks = new ArrayList<>();
    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = CustomerStatus.ACTIVE;
    }
 /*   public void updateName(String name) {
        this.name = name;
    }

    public void block() {
        this.status = CustomerStatus.BLOCKED;
    }    */
}
