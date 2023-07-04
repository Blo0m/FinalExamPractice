package com.acme.finalexampractice.platform.nature.service;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import com.acme.finalexampractice.platform.nature.domain.persistence.BigTreeRepository;
import com.acme.finalexampractice.platform.nature.domain.service.BigTreeService;
import com.acme.finalexampractice.platform.shared.exception.ResourceNotFoundException;
import com.acme.finalexampractice.platform.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Service
public class BigTreeServiceImpl implements BigTreeService {
    private static final String ENTITY = "BigTree";
    private final BigTreeRepository bigTreeRepository;
    private final Validator validator;

    public BigTreeServiceImpl(BigTreeRepository bigTreeRepository, Validator validator) {
        this.bigTreeRepository = bigTreeRepository;
        this.validator = validator;
    }

    @Override
    public List<BigTree> getAll() {
        return bigTreeRepository.findAll();
    }

    @Override
    public BigTree getById(Long id) {
        return bigTreeRepository.findBigTreeById(id);
    }

    @Override
    public BigTree create(BigTree bigTree) {
        Set<ConstraintViolation<BigTree>> violations = validator.validate(bigTree);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        LocalDate today = LocalDate.now();
        LocalDate bornAt = bigTree.getBornAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(Period.between(bornAt, today).getYears() < 50)
            throw new ResourceValidationException(ENTITY, "Big tree must be over 50 years old.");
        return bigTreeRepository.save(bigTree);
    }

    @Override
    public BigTree update(Long id, BigTree bigTree) {
        Set<ConstraintViolation<BigTree>> violations = validator.validate(bigTree);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return bigTreeRepository.findById(id)
                .map(bigTreeToUpdate -> bigTreeRepository.save(bigTreeToUpdate
                        .withUsername(bigTree.getUsername())
                        .withEmail(bigTree.getEmail())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return bigTreeRepository.findById(id)
                .map(bigTree -> {
                    bigTreeRepository.delete(bigTree);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
