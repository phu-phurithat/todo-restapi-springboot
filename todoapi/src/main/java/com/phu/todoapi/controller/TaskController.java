package com.phu.todoapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phu.todoapi.dtos.TaskDto;
import com.phu.todoapi.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(taskService.addTask(taskDto, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(taskService.updateTask(taskDto, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        taskService.deleteTask(id, username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(Authentication authentication) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(taskService.getAllTasks(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
