package com.acme.finalexampractice.platform.nature.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class UpdateGreenLeafResource {
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    private String scenario;
    private Long tip;
}
