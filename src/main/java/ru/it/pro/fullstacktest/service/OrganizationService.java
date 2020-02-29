package ru.it.pro.fullstacktest.service;

import org.jooq.Record3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Object findPageOfOrganizations(Pageable pageable, String organizationName) {
        return dao.findPageOfOrganizationsWithNameLike(pageable, organizationName);
    }

    public Page<Record3<Long, String, Integer>> findPageOfOrganizations(String organizationName, Pageable pageable) {
        return dao.findPageOfOrganizationsWithNameLike(organizationName, pageable);
    }

    //TODO later
    public Object findPageOfOrganizationsKeySet(Long lastId, String organizationName) {
        return organizationName == null ?
                dao.findPageOfOrganizationsKeySet(lastId) : dao.findPageOfOrganizationsKeySetWithNameLike(lastId, organizationName);
    }

    public List<Organization> findOrganizationsBases() {
        return dao.findOrganizationsBases();
    }

    public Organization findById(Long id) {
        return dao.findById(id);
    }

    public Organization findByName(String name) {
        return dao.findByName(name);
    }

    public List<Organization> findAffiliatedOrganizations(Long id) {
        return dao.findAffiliatedOrganizations(id);
    }

    public Organization update(Organization organization) {
        return dao.update(organization);
    }

    public Organization delete(Long id) {
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
