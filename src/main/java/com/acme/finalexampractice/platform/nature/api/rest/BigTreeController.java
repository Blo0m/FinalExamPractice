package com.acme.finalexampractice.platform.nature.api.rest;

import com.acme.finalexampractice.platform.nature.domain.service.BigTreeService;
import com.acme.finalexampractice.platform.nature.mapping.BigTreeMapper;
import com.acme.finalexampractice.platform.nature.resource.BigTreeResource;
import com.acme.finalexampractice.platform.nature.resource.CreateBigTreeResource;
import com.acme.finalexampractice.platform.nature.resource.GreenLeafResource;
import com.acme.finalexampractice.platform.nature.resource.UpdateBigTreeResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/trees", produces = "application/json")
public class BigTreeController {
    private final BigTreeService bigTreeService;
    private final BigTreeMapper mapper;

    public BigTreeController(BigTreeService bigTreeService, BigTreeMapper bigTreeMapper) {
        this.bigTreeService = bigTreeService;
        this.mapper = bigTreeMapper;
    }

    @GetMapping
    public Page<BigTreeResource> getAllBigTrees(Pageable pageable){
        return mapper.modelListPage(bigTreeService.getAll(), pageable);
    }

    @GetMapping("{treeId}")
    public BigTreeResource getBigTreeById(@PathVariable Long treeId){
        return mapper.toResource(bigTreeService.getById(treeId));
    }

    @PostMapping
    public BigTreeResource createBigTree(@RequestBody CreateBigTreeResource resource){
        return mapper.toResource(bigTreeService.create(mapper.toModel(resource)));
    }
    @PutMapping("{treeId}")
    public BigTreeResource updateBigTree(@PathVariable Long treeId, @RequestBody UpdateBigTreeResource resource){
        return mapper.toResource(bigTreeService.update(treeId, mapper.toModel(resource)));
    }

    @DeleteMapping("{treeId}")
    public ResponseEntity<?> deleteBigTree(@PathVariable Long treeId){
        return bigTreeService.delete(treeId);
    }
}
