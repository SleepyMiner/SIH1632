package org.au.careercove.api.data.repository;

import org.au.careercove.api.data.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
public interface AccountRepository extends PagingAndSortingRepository<Account, String> {

    @Override
    @RestResource(exported = false)
    void deleteById(String id);
}
