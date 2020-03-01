package ru.it.pro.fullstacktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.dao.EmployeeRepository;
import ru.it.pro.fullstacktest.dao.OrganizationRepository;
import ru.it.pro.fullstacktest.model.Employee;
import ru.it.pro.fullstacktest.model.Organization;

import java.util.List;

@Service
public class OrganizationService {

    private OrganizationRepository dao;
    private EmployeeRepository employeeRepository;

    @Autowired
    public OrganizationService(OrganizationRepository dao, EmployeeRepository employeeRepository) {
        this.dao = dao;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Organization add(Organization organization) {
        return dao.add(organization);
    }

    @Transactional(readOnly = true)
    public List<Organization> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public String findPageOfOrganizations(Pageable pageable, String organizationName) {
        return dao.findPageOfOrganizationsWithNameLike(pageable, organizationName);
    }

    //TODO later
    @Transactional(readOnly = true)
    public Object findPageOfOrganizationsKeySet(Long lastId, String organizationName) {
        return organizationName == null ?
                dao.findPageOfOrganizationsKeySet(lastId) : dao.findPageOfOrganizationsKeySetWithNameLike(lastId, organizationName);
    }

    @Transactional(readOnly = true)
    public List<Organization> findOrganizationsBases() {
        return dao.findOrganizationsBases();
    }

    @Transactional(readOnly = true)
    public Organization findById(Long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public Organization findByName(String name) {
        return dao.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Organization> findAffiliatedOrganizations(Long id) {
        return dao.findAffiliatedOrganizations(id);
    }

    @Transactional
    public Organization update(Organization organization) {
        return dao.update(organization);
    }

    @Transactional
    public Organization delete(Long id) {
        List<Organization> affiliatedOrganizations = dao.findAffiliatedOrganizations(id);
        List<Employee> workers = employeeRepository.findEmployeesOfOrganization(id);


        if (affiliatedOrganizations.isEmpty() && workers.isEmpty()) {
            return dao.delete(id);
        } else {
            return null;
        }
    }

}
