package com.acme.finalexampractice.platform.nature.resource;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreateGreenLeafResource {
    @NotNull
    @NotBlank
    private String title;

    private String scenario;
    private Long tip;

}
