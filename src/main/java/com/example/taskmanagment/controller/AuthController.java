package com.example.taskmanagment.controller;

import com.example.taskmanagment.dto.LoginRequest;
import com.example.taskmanagment.dto.Response;
import com.example.taskmanagment.entity.User;
import com.example.taskmanagment.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest){
        Response response = userService.loginUser(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user){
        Response response = userService.createUser(user);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
