package ru.it.pro.fullstacktest.dao;

import ru.it.pro.fullstacktest.model.Organization;

import java.util.List;

public interface OrganizationRepository {

    Organization add(Organization entity);

    Organization update(Organization entity);

    Organization delete(Integer id);

    Organization findByName(String name);

    Organization findById(Integer id);

    List<Organization> findAll();

    List<Organization> findPageOfOrganizations(int page);

    List<Organization> findPageOfOrganizationsWithNameLike(int page, String organizationName);

    List<Organization> findPageOfOrganizationsKeySet(Integer lastId);

    List<Organization> findPageOfOrganizationsKeySetWithNameLike(Integer lastId, String organizationName);

    List<Organization> findOrganizationsBases();

    List<Organization> findAffiliatedOrganizations(Integer id);
}