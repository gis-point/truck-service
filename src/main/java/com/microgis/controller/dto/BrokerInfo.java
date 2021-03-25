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
    private String brokerCompany;

    /**
     * broker company address
     */
    @NotNull
    private String brokerAddress;

    /**
     * broker company phone number
     */
    @NotNull
    private String brokerPhone;

    /**
     * broker company extension number
     */
    private Integer brokerPhoneExtension;

    /**
     * broker name
     */
    @NotNull
    private String brokerName;

}
