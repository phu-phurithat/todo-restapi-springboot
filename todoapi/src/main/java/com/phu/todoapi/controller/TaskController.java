package com.phu.todoapi.controller;

import com.phu.todoapi.DTOs.AddTaskDto;
import com.phu.todoapi.entity.Tasks;
import com.phu.todoapi.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Tasks> addTask(@RequestBody AddTaskDto addTaskDto, Authentication authentication) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(taskService.addTask(addTaskDto,username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
