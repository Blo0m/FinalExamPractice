package com.acme.finalexampractice.platform.nature.service;

import com.acme.finalexampractice.platform.nature.domain.model.GreenLeaf;
import com.acme.finalexampractice.platform.nature.domain.persistence.BigTreeRepository;
import com.acme.finalexampractice.platform.nature.domain.persistence.GreenLeafRepository;
import com.acme.finalexampractice.platform.nature.domain.service.GreenLeafService;
import com.acme.finalexampractice.platform.shared.exception.ResourceNotFoundException;
import com.acme.finalexampractice.platform.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GreenLeafServiceImpl implements GreenLeafService {
    private static final String ENTITY = "GreenLeaf";
    private final GreenLeafRepository greenLeafRepository;
    private final BigTreeRepository bigTreeRepository;
    private final Validator validator;

    public GreenLeafServiceImpl(GreenLeafRepository greenLeafRepository, BigTreeRepository bigTreeRepository, Validator validator) {
        this.greenLeafRepository = greenLeafRepository;
        this.bigTreeRepository = bigTreeRepository;
        this.validator = validator;
    }

    @Override
    public List<GreenLeaf> getAllByBigTreeId(Long treeId) {
        return greenLeafRepository.findAllByBigTreeId(treeId);
    }

    @Override
    public GreenLeaf getById(Long id) {
        return greenLeafRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public GreenLeaf create(GreenLeaf greenLeaf, Long treeId) {
        Set<ConstraintViolation<GreenLeaf>> violations = validator.validate(greenLeaf);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(greenLeafRepository.existsByTitleAndBigTreeId(greenLeaf.getTitle(), treeId))
            throw new ResourceValidationException(ENTITY, "A green leaf with that name already exists.");
        return bigTreeRepository.findById(treeId)
                .map(bigTree -> {
                    greenLeaf.setBigTree(bigTree);
                    return greenLeafRepository.save(greenLeaf);
                }).orElseThrow(() -> new ResourceNotFoundException("Big Tree", treeId));
    }
    @Override
    public GreenLeaf update(Long treeId, Long id, GreenLeaf greenLeaf) {
        Set<ConstraintViolation<GreenLeaf>> violations = validator.validate(greenLeaf);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(!bigTreeRepository.existsById(treeId))
            throw new ResourceNotFoundException("Big Tree", treeId);
        return greenLeafRepository.findById(id)
                .map(existingGreenLeaf -> greenLeafRepository.save(existingGreenLeaf
                        .withTitle(greenLeaf.getTitle())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long treeId, Long id) {
        return greenLeafRepository.findByIdAndBigTreeId(id, treeId)
                .map(greenLeaf -> {
                    greenLeafRepository.delete(greenLeaf);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
