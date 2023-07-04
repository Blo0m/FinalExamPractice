package com.acme.finalexampractice.platform.nature.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "green_leaves")
public class GreenLeaf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    private String scenario;
    private Long tip;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BigTree.class, optional = false)
    @JoinColumn(name = "big_tree_id", nullable = false)
    private BigTree bigTree;
}
