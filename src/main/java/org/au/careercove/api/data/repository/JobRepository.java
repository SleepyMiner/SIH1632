package org.au.careercove.api.data.repository;

import org.au.careercove.api.data.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "jobs", path = "jobs")
public interface JobRepository extends PagingAndSortingRepository<Job, String> {

    @Transactional
    List<Job> findAllByTitleContaining(@Param("title") String title);

    @Transactional
    List<Job> findAllByOrganizationNameContaining(@Param("organizationName") String organizationName);

    @Transactional
    @Query(value = "SELECT * FROM job j " +
                   "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
                   "AND (:organizationName IS NULL OR j.org_name LIKE %:organizationName%) " +
                   "AND (:workMode IS NULL OR j.work_mode = :workMode) " +
                   "AND (:sector IS NULL OR j.sector = :sector) " +
                   "AND (:workHours IS NULL OR j.worhours = :workHours)",
           nativeQuery = true)
    List<Job> findAllByTitleOrOrganizationNameOrWorkModeOrSectorOrSkillsOrWorkHours(
        @Param("title") String title,
        @Param("organizationName") String organizationName,
        @Param("workMode") String workMode,
        @Param("sector") String sector,
        @Param("workHours") String workHours
    );

    

    @Override
    @RestResource(exported = false)
    void deleteById(String id);
}
