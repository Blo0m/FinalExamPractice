package com.acme.finalexampractice.platform.nature.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class UpdateBigTreeResource {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String email;

    private String firstName;
    private String lastName;
    private String gender;
    private Date bornAt;
}
