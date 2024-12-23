package com.phu.todoapi.DTOs;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskDto {

    private Long id;
    private String title;
    private String content;
    private String category;
    private String priority;
    private String dueDate;
    private String status;

}
