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

    public Object findPageOfOrganizations(int page, String organizationName) {
        return dao.findPageOfOrganizationsWithNameLike(page, organizationName);
    }

    //TODO later
    public Object findPageOfOrganizationsKeySet(Integer lastId, String organizationName) {
        return organizationName == null ?
                dao.findPageOfOrganizationsKeySet(lastId) : dao.findPageOfOrganizationsKeySetWithNameLike(lastId, organizationName);
    }

    public List<Organization> findOrganizationsBases() {
        return dao.findOrganizationsBases();
    }

    public Organization findById(Integer id) {
        return dao.findById(id);
    }

    public Organization findByName(String name) {
        return dao.findByName(name);
    }

    public List<Organization> findAffiliatedOrganizations(Integer id) {
        return dao.findAffiliatedOrganizations(id);
    }

    public Organization update(Organization organization) {
        return dao.update(organization);
    }

    public Organization delete(Integer id) {
        List<Organization> affiliatedOrganizations = dao.findAffiliatedOrganizations(id);

        if (affiliatedOrganizations.isEmpty()) {
            System.out.println("can delete");
            return dao.delete(id);
        }
        else {
            System.out.println("can't delete");
            return null;
        }
    }



}
