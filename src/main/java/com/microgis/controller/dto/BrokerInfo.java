package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerInfo {

    /**
     * broker company name
     */
    @NotNull
    private String company;

    /**
     * broker company address
     */
    @NotNull
    private String address;

    /**
     * broker company phone number
     */
    @NotNull
    private String phone;

    /**
     * broker company extension number
     */
    private Integer phoneExtension;

    /**
     * broker name
     */
    @NotNull
    private String name;

}
