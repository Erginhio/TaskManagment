package com.example.taskmanagment.service.impl;

import com.example.taskmanagment.dto.Response;
import com.example.taskmanagment.dto.UserDto;
import com.example.taskmanagment.entity.User;
import com.example.taskmanagment.exeption.OurExeption;
import com.example.taskmanagment.repo.UserRepository;
import com.example.taskmanagment.service.repository.IUserService;
import com.example.taskmanagment.utils.Utils;
import jdk.jfr.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.example.taskmanagment.utils.Utils.*;


@Component
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response createUser(User user) {
        Response response = new Response();


        try {
            if(user.getRole() == null || user.getRole().isBlank()){
                user.setRole("USER");
            }

            if(userRepository.existsByUsername(user.getUsername())){
                throw new OurExeption(user.getUsername() + " Already Exists");
            }

            if(userRepository.existsByEmail(user.getEmail())){
                throw new OurExeption(user.getEmail() + " Already Exists");
            }
            user.setCreated_at(Utils.currentDateTime());
            user.setPassword(Utils.MD5(user.getPassword()));
            User createdUser = userRepository.save(user);
            UserDto userDto = mapUserEntityToUserDTO(createdUser);
            response.setStatusCode(200);
            response.setBody(userDto);
            response.setMessage("Success");
        }catch (OurExeption e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public Response getAllUsers() {

        Response response = new Response();

        try{
            List<User> userList = userRepository.findAll();
            List<UserDto> userDtoList = mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("Success");
            response.setBody(userDtoList);

        }catch (OurExeption e){
            response.setStatusCode(500);
            response.setMessage("Error getting users " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getUserByID(Long userID) {
        Response response = new Response();

        try{
            User user = userRepository.findById(userID).orElseThrow(() -> new OurExeption("User Not Found"));
            UserDto userDto = mapUserEntityToUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("Success");
            response.setBody(userDto);
        }catch (OurExeption e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public Response deleteUserByID(Long userID) {
        Response response = new Response();

        try{
            User user = userRepository.findById(userID).orElseThrow(() -> new OurExeption("User Not Found"));
            userRepository.deleteById(user.getId());
            response.setStatusCode(200);
            response.setMessage("User delete successful");
            response.setBody(Collections.emptyList());
        }catch (OurExeption e){
            response.setStatusCode(500);
            response.setMessage("The user has not been delete" + e.getMessage());
        }

        return response;
    }


}
