package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressLine {

    /**
     * address where from/to deliver
     */
    @NotNull
    private String address;

    /**
     * city where from/to deliver
     */
    @NotNull
    private String city;

    /**
     * state where from/to deliver
     */
    @NotNull
    private String state;

    /**
     * time when should deliver/pick-up
     */
    @NotNull
    private String time;

    /**
     * address from/to zipcode
     */
    @NotNull
    private String zipcode;

    /**
     * company where from/to deliver
     */
    @NotNull
    private String company;

}