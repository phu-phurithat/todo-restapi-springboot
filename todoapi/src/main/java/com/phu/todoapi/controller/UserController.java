package com.phu.todoapi.controller;

import com.phu.todoapi.entity.Users;
import com.phu.todoapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/me")
    public ResponseEntity<Users> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users currentUser = (Users) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> allUsers() {
        List <Users> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
