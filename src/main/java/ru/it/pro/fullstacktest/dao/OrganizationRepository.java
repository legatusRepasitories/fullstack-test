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


}
