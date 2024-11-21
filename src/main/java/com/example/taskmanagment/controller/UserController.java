package com.example.taskmanagment.controller;

import com.example.taskmanagment.dto.LoginRequest;
import com.example.taskmanagment.dto.Response;
import com.example.taskmanagment.entity.User;
import com.example.taskmanagment.service.repository.IUserService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.taskmanagment.dto.Response;

@RestController
@RequestMapping("users")
public class UserController {


    @Autowired
    private IUserService userService;

    @GetMapping("getAll")
    public  ResponseEntity<Response> getAllUsers(){
        Response response = userService.getAllUsers();
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @GetMapping()
    public ResponseEntity<Response> getUserByID(@RequestParam long id){
        Response response = userService.getUserByID(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping()
    public ResponseEntity<Response> deleteUserByID(@RequestParam long id){
        Response response = userService.deleteUserByID(id);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Response> udpateUser(@PathVariable long id,
                                               @RequestBody User user){
        Response response = userService.updateUser(id, user);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
