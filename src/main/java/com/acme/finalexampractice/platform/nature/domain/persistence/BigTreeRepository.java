package com.acme.finalexampractice.platform.nature.domain.persistence;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BigTreeRepository extends JpaRepository<BigTree, Long> {
     BigTree findBigTreeById (Long id);
}
