package org.au.careercove.api.data.repository;

import org.au.careercove.api.data.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    @RestResource(path = "details", rel = "getByMobile")
    List<User> findAllByMobileNumber(@Param("mobile") String mobileNumber);

    @RestResource(exported = false)
    List<User> findAllByEmail(String email);

    @RestResource(exported = false)
    List<User> findAllByMobileNumberAndPassword(String mobile, String password);

    @RestResource(exported = false)
    List<User> findAllByEmailAndPassword(String email, String password);

    @Override
    @RestResource(exported = false)
    void deleteById(String id);
}
