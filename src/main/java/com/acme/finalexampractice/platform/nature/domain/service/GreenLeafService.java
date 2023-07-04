package com.acme.finalexampractice.platform.nature.domain.service;

import com.acme.finalexampractice.platform.nature.domain.model.GreenLeaf;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GreenLeafService {
    List<GreenLeaf> getAllByBigTreeId(Long id);
    GreenLeaf getById(Long id);
    GreenLeaf create(GreenLeaf greenLeaf, Long treeId);
    GreenLeaf update(Long treeId, Long id, GreenLeaf greenLeaf);
    ResponseEntity<?> delete(Long treeId, Long id);
}
