package com.example.taskmanagment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Integer statusCode;
    private String message;
    private String token;
    private Object body;
}
