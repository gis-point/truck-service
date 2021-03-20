package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoTo {

    @NotNull
    private String addressTo;

    @NotNull
    private String cityTo;

    @NotNull
    private String stateTo;

    @NotNull
    private String timeTo;

    @NotNull
    private String zipcodeTo;

}