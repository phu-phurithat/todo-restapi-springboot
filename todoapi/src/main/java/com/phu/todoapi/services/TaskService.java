package com.phu.todoapi.services;

import com.phu.todoapi.DTOs.TaskDto;
import com.phu.todoapi.entity.Tasks;
import com.phu.todoapi.entity.Users;
import com.phu.todoapi.repos.TaskRepo;
import com.phu.todoapi.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public TaskDto addTask(TaskDto input, String username) {
        Users user = getUserByUsername(username);
        Tasks task = TaskMapper.toEntity(input, user);
        return TaskMapper.toDto(taskRepo.save(task));
    }

    public TaskDto updateTask(TaskDto input, String username) {
        Tasks existedTask = taskRepo.findById(input.getId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Users user = getUserByUsername(username);
        Tasks updatedTask = TaskMapper.toEntity(input, user);
        updatedTask.setId(existedTask.getId());
        return TaskMapper.toDto(taskRepo.save(updatedTask));
    }

    private Users getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void deleteTask(Long taskId, String username) {
        Tasks task = taskRepo.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        if (!task.getUser().getUsername().equals(username)) {
            throw new SecurityException("You are not authorized to delete this task");
        }

        taskRepo.delete(task);
    }

    public List<TaskDto> getAllTasks(String username) {
        Users user = getUserByUsername(username);
        List<Tasks> tasks = taskRepo.findAllByUserId(user.getId());
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Tasks task : tasks) {
            taskDtos.add(TaskMapper.toDto(task));
        }
        return taskDtos;
    }
}
