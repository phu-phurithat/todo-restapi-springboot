package com.phu.todoapi.services;

import com.phu.todoapi.dtos.TaskDto;
import com.phu.todoapi.entity.Tasks;
import com.phu.todoapi.entity.Users;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskMapper {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Tasks toEntity(TaskDto taskDto, Users user) {
        return new Tasks()
                .setId(taskDto.getId())
                .setTitle(taskDto.getTitle())
                .setContent(taskDto.getContent())
                .setCategory(taskDto.getCategory())
                .setPriority(parsePriority(taskDto.getPriority()))
                .setDueDate(parseDueDate(taskDto.getDueDate()))
                .setStatus(parseStatus(taskDto.getStatus()))
                .setUser(user);
    }

    private static Tasks.TaskStatus parseStatus(String status) {
        try {
            return switch (status) {
                case "PENDING" -> Tasks.TaskStatus.PENDING;
                case "IN_PROGRESS" -> Tasks.TaskStatus.IN_PROGRESS;
                case "COMPLETED" -> Tasks.TaskStatus.COMPLETED;
                default -> throw new IllegalArgumentException("Invalid status");
            };
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status");
        }
    }

    private static LocalDateTime parseDueDate(String dueDate) {
        try {
            return LocalDateTime.parse(dueDate, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid due date format");
        }
    }

    private static Tasks.TaskPriority parsePriority(String priority) {
        try {
            return switch (priority) {
                case "LOW" -> Tasks.TaskPriority.LOW;
                case "MEDIUM" -> Tasks.TaskPriority.MEDIUM;
                case "HIGH" -> Tasks.TaskPriority.HIGH;
                default -> throw new IllegalArgumentException("Invalid priority");
            };
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority");
        }
    }

    public static TaskDto toDto(Tasks task) {
        return new TaskDto()
                .setId(task.getId())
                .setTitle(task.getTitle())
                .setContent(task.getContent())
                .setCategory(task.getCategory())
                .setPriority(formatPriority(task.getPriority()))
                .setDueDate(formatDueDate(task.getDueDate()))
                .setStatus(formatStatus(task.getStatus()));
    }

    private static String formatPriority(Tasks.TaskPriority priority) {
        return switch (priority) {
            case LOW -> "LOW";
            case MEDIUM -> "MEDIUM";
            case HIGH -> "HIGH";
        };
    }

    private static String formatStatus(Tasks.TaskStatus status) {
        return switch (status) {
            case PENDING -> "PENDING";
            case IN_PROGRESS -> "IN_PROGRESS";
            case COMPLETED -> "COMPLETED";
        };
    }

    private static String formatDueDate(LocalDateTime dueDate) {
        return dueDate.format(DATE_TIME_FORMATTER);
    }
}
