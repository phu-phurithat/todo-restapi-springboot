package com.phu.todoapi.DTOs;

import lombok.Data;

@Data
public class AddTaskDto {

    private String title;
    private String content;
    private String category;
    private int priority;
    private String dueDate;
    private String status;

}
