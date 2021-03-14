package com.microgis.controller.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Login {

    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 1, max = 50)
    private String password;

}