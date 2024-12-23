package com.phu.todoapi.DTOs;

import lombok.Data;

@Data
public class AddTaskDto {

    private String title;
    private String content;
    private String category;
    private String priority;
    private String dueDate;
    private String status;

}
