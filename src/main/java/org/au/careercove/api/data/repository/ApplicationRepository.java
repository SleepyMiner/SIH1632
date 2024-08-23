package org.au.careercove.api.data.repository;

import org.au.careercove.api.data.model.Application;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;

@RepositoryRestResource(collectionResourceRel = "applications", path = "applications")

    public interface ApplicationRepository extends PagingAndSortingRepository<Application, String> {
    
        @RestResource(exported = false)
        List<Application> findAllByApplicantName(String applicantName);
    
        @RestResource(exported = false)
        List<Application> findAllByType(String type);
    
        @RestResource(exported = false)
        List<Application> findAllByCompanyName(String companyName);
    
    
        @Override
        @RestResource(exported = false)
        void deleteById(String id);
    }
    

