package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "deals")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Deal extends BaseEntity{
    @Column(nullable = false, length = 100)
    private String title;
    @Lob
    private String description;
    @Column(nullable = false)
    private Double amount;
    @Enumerated(EnumType.STRING)
    private DealStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    public Deal(String title, String description, Double amount) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.status = DealStatus.NEW;
    }
}
