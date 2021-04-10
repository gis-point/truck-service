package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    /**
     * volo domain
     */
    private String domain;

    /**
     * driver email
     */
    @NotNull
    private String email;

    /**
     * driver password
     */
    @NotNull
    private String password;

}