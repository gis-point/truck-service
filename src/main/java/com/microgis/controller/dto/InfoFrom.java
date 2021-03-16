package com.microgis.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoFrom {

    @NotNull
    private String addressLineFrom;

    @NotNull
    private String cityFrom;

    @NotNull
    private String stateFrom;

    @NotNull
    private String timeFrom;

}