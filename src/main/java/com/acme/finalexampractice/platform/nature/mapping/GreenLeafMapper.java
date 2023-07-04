package com.acme.finalexampractice.platform.nature.mapping;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import com.acme.finalexampractice.platform.nature.domain.model.GreenLeaf;
import com.acme.finalexampractice.platform.nature.resource.*;
import com.acme.finalexampractice.platform.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class GreenLeafMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public GreenLeafResource toResource(GreenLeaf model) { return mapper.map(model, GreenLeafResource.class); }

    public Page<GreenLeafResource> modelListPage(List<GreenLeaf> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, GreenLeafResource.class), pageable, modelList.size());
    }

    public GreenLeaf toModel(CreateGreenLeafResource resource) { return mapper.map(resource, GreenLeaf.class); }
    public GreenLeaf toModel(UpdateGreenLeafResource resource) { return mapper.map(resource, GreenLeaf.class); }

}
