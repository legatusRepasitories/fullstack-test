package ru.it.pro.fullstacktest.dao;

import org.jooq.Record3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.it.pro.fullstacktest.model.Organization;

import java.util.List;

public interface OrganizationRepository {

    Organization add(Organization entity);

    Organization update(Organization entity);

    Organization delete(Long id);

    Organization findByName(String name);

    Organization findById(Long id);

    List<Organization> findAll();

    Object findPageOfOrganizationsWithNameLike(Pageable pageable, String organizationName);

    Page<Record3<Long, String, Integer>> findPageOfOrganizationsWithNameLike(String organizationName, Pageable pageable);

    List<Organization> findPageOfOrganizationsKeySet(Long lastId);

    List<Organization> findPageOfOrganizationsKeySetWithNameLike(Long lastId, String organizationName);

    List<Organization> findOrganizationsBases();

    List<Organization> findAffiliatedOrganizations(Long id);
}
