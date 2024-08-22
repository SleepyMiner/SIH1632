package org.au.careercove.api.data.repository;

import org.au.careercove.api.data.model.Internship;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "internships", path = "internships")
public interface InternshipRepository extends PagingAndSortingRepository<Internship, String> {

    @Override
    @RestResource(exported = false)
    void deleteById(String id);
}
