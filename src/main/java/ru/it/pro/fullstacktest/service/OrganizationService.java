package ru.it.pro.fullstacktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.it.pro.fullstacktest.dao.OrganizationRepository;
import ru.it.pro.fullstacktest.model.Organization;

import java.util.List;

@Service
public class OrganizationService {

    private OrganizationRepository dao;

    @Autowired
    public OrganizationService(OrganizationRepository dao) {
        this.dao = dao;
    }

    public Organization add(Organization organization) {
        return dao.add(organization);
    }

    public List<Organization> findAll() {
        return dao.findAll();
    }

    public List<Organization> findPageOfOrganizations(int page, String organizationName) {
        return organizationName == null ?
                dao.findPageOfOrganizations(page) : dao.findPageOfOrganizationsWithNameLike(page, organizationName);
    }



    public Organization findById(Integer id) {
        return dao.findById(id);
    }

    public Organization findByName(String name) {
        return dao.findByName(name);
    }

    public Organization update(Organization organization) {
        return dao.update(organization);
    }

    public Organization delete(Integer id) {
        return dao.delete(id);
    }
}
