package ru.it.pro.fullstacktest.dao;

import org.jooq.Record3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.it.pro.fullstacktest.model.Organization;

import java.util.List;

public interface OrganizationRepository {

    Organization add(Organization entity);

    Organization update(Organization entity);

    Organization delete(Integer id);

    Organization findByName(String name);

    Organization findById(Integer id);

    List<Organization> findAll();

    Object findPageOfOrganizationsWithNameLike(int page, String organizationName);

    Page<Record3<Integer, String, Integer>> findPageOfOrganizationsWithNameLike(String organizationName, Pageable pageable);

    List<Organization> findPageOfOrganizationsKeySet(Integer lastId);

    List<Organization> findPageOfOrganizationsKeySetWithNameLike(Integer lastId, String organizationName);

    List<Organization> findOrganizationsBases();

    List<Organization> findAffiliatedOrganizations(Integer id);
}
