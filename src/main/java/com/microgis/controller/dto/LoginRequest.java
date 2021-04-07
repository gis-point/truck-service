package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;

    /**
     * driver password
     */
    private String password;

}