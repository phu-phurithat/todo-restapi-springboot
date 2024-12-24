package com.phu.todoapi.controller;

import com.phu.todoapi.DTOs.TaskDto;
import com.phu.todoapi.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

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
