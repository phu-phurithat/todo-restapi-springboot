package com.phu.todoapi.services;

import com.phu.todoapi.DTOs.AddTaskDto;
import com.phu.todoapi.entity.Tasks;
import com.phu.todoapi.entity.Users;
import com.phu.todoapi.repos.TaskRepo;
import com.phu.todoapi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phu.todoapi.entity.Tasks.TaskStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;

    public Tasks addTask(AddTaskDto input, String username) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Users user = userRepo.findByUsername(username).orElseThrow();
        LocalDateTime dueDate = LocalDateTime.parse(input.getDueDate(), formatter);
        TaskStatus status = switch (input.getStatus()) {
            case "PENDING" -> TaskStatus.PENDING;
            case "IN_PROGRESS" -> TaskStatus.IN_PROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            default -> throw new IllegalArgumentException("Invalid status");
        };
        Tasks.TaskPriority priority = switch (input.getPriority()) {
            case "LOW" -> Tasks.TaskPriority.LOW;
            case "MEDIUM" -> Tasks.TaskPriority.MEDIUM;
            case "HIGH" -> Tasks.TaskPriority.HIGH;
            default -> throw new IllegalArgumentException("Invalid priority");
        };
        Tasks task = new Tasks()
                .setTitle(input.getTitle())
                .setContent(input.getContent())
                .setCategory(input.getCategory())
                .setPriority(priority)
                .setDueDate(dueDate)
                .setStatus(status)
                .setUser(user);

        return taskRepo.save(task);
    }
}
