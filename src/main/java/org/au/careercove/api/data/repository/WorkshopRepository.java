package org.au.careercove.api.data.repository;

import java.util.List;

import org.au.careercove.api.data.model.Workshop;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "workshops", path = "workshops")
public interface WorkshopRepository  extends PagingAndSortingRepository<Workshop, String> {

    @RestResource(exported = false)
    List<Workshop> findAllByTitle(String title);

    @RestResource(exported = false)
    List<Workshop> findAllByInstructorName(String instructorName);

    @RestResource(exported = false)
    List<Workshop> findAllByOrganizationID(String organizationID);

    
    @Override
    @RestResource(exported = false)
    void deleteById(String id);
}   
