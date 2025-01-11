package com.phu.todoapi.services;

import com.phu.todoapi.dtos.LoginUserDto;
import com.phu.todoapi.dtos.RegisterUserDto;
import com.phu.todoapi.entity.Users;
import com.phu.todoapi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public Users signup(RegisterUserDto input) {
        Users user = new Users()
                .setName(input.getName())
                .setUsername(input.getUsername())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepo.save(user);
    }

    public Users authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepo.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
