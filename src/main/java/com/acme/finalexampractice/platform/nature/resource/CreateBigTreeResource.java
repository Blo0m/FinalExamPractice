package com.acme.finalexampractice.platform.nature.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreateBigTreeResource {
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    private Date bornAt;

    private String firstName;
    private String lastName;
    private String gender;
}
