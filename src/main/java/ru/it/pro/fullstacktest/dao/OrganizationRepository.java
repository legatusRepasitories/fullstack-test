package ru.it.pro.fullstacktest.dao;

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

    String findPageOfOrganizationsWithNameLike(Pageable pageable, String organizationName);

    String findKeySetOfOrganizationsWithNameLike(Long lastId, String organizationName);

    List<Organization> findOrganizationsBases();

    List<Organization> findAffiliatedOrganizations(Long id);
}
