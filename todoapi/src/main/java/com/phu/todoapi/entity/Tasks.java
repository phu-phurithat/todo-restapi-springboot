package com.phu.todoapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String Category;

    @Column(nullable = false)
    private Timestamp dueDate;

    private int priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    public enum TaskStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
