package com.phu.todoapi.services;

import com.phu.todoapi.entity.Users;
import com.phu.todoapi.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<Users> allUsers() {
        List<Users> users = new ArrayList<>();

        userRepo.findAll().forEach(users::add);

        return users;
    }
}
