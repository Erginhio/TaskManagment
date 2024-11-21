package com.example.taskmanagment.service.repository;

import com.example.taskmanagment.dto.LoginRequest;
import com.example.taskmanagment.dto.Response;
import com.example.taskmanagment.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {

    Response createUser(User user);

    Response getAllUsers();

    Response getUserByID(Long userID);

    Response deleteUserByID(Long id);

    Response updateUser(Long id, User user);

    Response loginUser(LoginRequest loginRequest);
}
