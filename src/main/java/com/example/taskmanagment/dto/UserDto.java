package com.example.taskmanagment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private Timestamp created_at;
    private Timestamp updated_at;
}
