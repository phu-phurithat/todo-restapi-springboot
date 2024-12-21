package com.phu.todoapi.DTOs;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String username;

    private String email;

    private String password;

    private String name;

}
