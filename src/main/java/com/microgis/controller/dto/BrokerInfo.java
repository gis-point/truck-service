package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerInfo {

    @NotNull
    private String brokerCompany;

    @NotNull
    private String brokerAddress;

    @NotNull
    private String brokerPhone;

    private String brokerPhoneExtension;

    @NotNull
    private String brokerName;

}
