package com.acme.finalexampractice.platform.nature.domain.persistence;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import com.acme.finalexampractice.platform.nature.domain.model.GreenLeaf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GreenLeafRepository extends JpaRepository<GreenLeaf, Long> {
    List<GreenLeaf> findAllByBigTreeId(Long id);
    Optional<GreenLeaf> findByIdAndBigTreeId(Long id, Long treeId);

    Boolean existsByTitleAndBigTreeId(String title, Long treeId);
}
