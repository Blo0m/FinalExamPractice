package com.acme.finalexampractice.platform.nature.mapping;

import com.acme.finalexampractice.platform.nature.domain.model.BigTree;
import com.acme.finalexampractice.platform.nature.resource.BigTreeResource;
import com.acme.finalexampractice.platform.nature.resource.CreateBigTreeResource;
import com.acme.finalexampractice.platform.nature.resource.UpdateBigTreeResource;
import com.acme.finalexampractice.platform.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class BigTreeMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public BigTreeResource toResource(BigTree model) { return mapper.map(model, BigTreeResource.class); }

    public Page<BigTreeResource> modelListPage(List<BigTree> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, BigTreeResource.class), pageable, modelList.size());
    }

    public BigTree toModel(CreateBigTreeResource resource) { return mapper.map(resource, BigTree.class); }
    public BigTree toModel(UpdateBigTreeResource resource) { return mapper.map(resource, BigTree.class); }

}
