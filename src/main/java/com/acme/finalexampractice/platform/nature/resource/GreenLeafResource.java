package com.acme.finalexampractice.platform.nature.resource;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class GreenLeafResource {
    private Long id;
    private String title;
    private String scenario;
    private Long tip;
}
