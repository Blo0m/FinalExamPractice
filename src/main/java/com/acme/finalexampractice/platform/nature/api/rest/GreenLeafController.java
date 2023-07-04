package com.acme.finalexampractice.platform.nature.api.rest;

import com.acme.finalexampractice.platform.nature.domain.service.GreenLeafService;
import com.acme.finalexampractice.platform.nature.mapping.GreenLeafMapper;
import com.acme.finalexampractice.platform.nature.resource.CreateGreenLeafResource;
import com.acme.finalexampractice.platform.nature.resource.GreenLeafResource;
import com.acme.finalexampractice.platform.nature.resource.UpdateGreenLeafResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/trees/{treeId}/leafs", produces = "application/json")
public class GreenLeafController {
    private final GreenLeafMapper mapper;
    private final GreenLeafService greenLeafService;

    public GreenLeafController(GreenLeafMapper greenLeafMapper, GreenLeafService greenLeafService) {
        this.mapper = greenLeafMapper;
        this.greenLeafService = greenLeafService;
    }

    @GetMapping
    public Page<GreenLeafResource> getAllGreenLeavesByBigTreeId (@PathVariable Long treeId, Pageable pageable){
        return  mapper.modelListPage(greenLeafService.getAllByBigTreeId(treeId), pageable);
    }

    @GetMapping({"{greenLeafId}"})
    public GreenLeafResource getGreenLeafById (@PathVariable Long greenLeafId){
        return mapper.toResource(greenLeafService.getById(greenLeafId));
    }

    @PostMapping
    public GreenLeafResource createGreenLeaf(@PathVariable Long treeId,
                                             @RequestBody CreateGreenLeafResource resource){
        return mapper.toResource(greenLeafService.create(mapper.toModel(resource), treeId));
    }

    @PutMapping("{greenLeafId}")
    public GreenLeafResource updateGreenLeaf(@PathVariable Long treeId,
                                             @PathVariable Long greenLeafId,
                                             @RequestBody UpdateGreenLeafResource resource){
        return mapper.toResource(greenLeafService.update(treeId, greenLeafId, mapper.toModel(resource)));
    }

    @DeleteMapping("{greenLeafId}")
    public ResponseEntity<?> deleteGreenLeaf(@PathVariable Long treeId,
                                             @PathVariable Long greenLeafId){
        return greenLeafService.delete(treeId, greenLeafId);
    }
}
