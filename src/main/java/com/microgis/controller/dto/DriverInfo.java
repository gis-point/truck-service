package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfo {

    /**
     * driver login
     */
    private String login;

    /**
     * driver full name
     */
    @NotNull
    private String name;

    /**
     * driver contact email
     */
    private String email;

    /**
     * driver phone number
     */
    @NotNull
    private String phone;

}