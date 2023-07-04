package com.acme.finalexampractice.platform.nature.resource;

import com.acme.finalexampractice.platform.nature.domain.model.GreenLeaf;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class BigTreeResource {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Date bornAt;
    private Set<GreenLeafResource> greenLeaves;
}
