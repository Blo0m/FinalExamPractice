package com.acme.finalexampractice.platform.nature.domain.service;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BigTreeService {
    List<BigTree> getAll();
    BigTree getById(Long id);
    BigTree create(BigTree bigTree);
    BigTree update(Long id, BigTree bigTree);
    ResponseEntity<?> delete(Long id);
}
