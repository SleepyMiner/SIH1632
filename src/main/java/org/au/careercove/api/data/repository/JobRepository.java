package org.au.careercove.api.data.repository;

import org.au.careercove.api.data.model.Job;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "jobs", path = "jobs")
public interface JobRepository extends PagingAndSortingRepository<Job, String> {

    @Override
    @RestResource(exported = false)
    void deleteById(String id);
}
