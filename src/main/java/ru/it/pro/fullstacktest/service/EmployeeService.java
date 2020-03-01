package ru.it.pro.fullstacktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.dao.EmployeeRepository;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }


    @Transactional
    public Employee add(Employee employee) {

        return isChiefAbsentOrInSameOrganization(employee) ? employeeRepository.add(employee) : null;

    }


    @Transactional(readOnly = true)
    public Employee findById(Long id) {

        return employeeRepository.findById(id);

    }


    @Transactional(readOnly = true)
    public String findPageOfEmployees(Pageable pageable, String name) {

        return employeeRepository.findPageOfEmployeesWithNameLike(pageable, name);

    }

    @Transactional(readOnly = true)
    public String findKeySetOfEmployeesWithNameLike(Long lastId, String name) {
        return employeeRepository.findKeySetOfEmployeesWithNameLike(lastId, name);
    }


    @Transactional(readOnly = true)
    public List<Employee> findEmployeeOfOrganization(Long id) {

        return employeeRepository.findEmployeesOfOrganization(id);

    }


    @Transactional
    public Employee update(Employee employee) {

        return isChiefAbsentOrInSameOrganization(employee) ? employeeRepository.update(employee) : null;

    }

    @Transactional(readOnly = true)
    public List<Employee> findChiefWorkers(Long id) {

        return employeeRepository.findEmployeeWorkers(id);

    }

    @Transactional(readOnly = true)
    public List<Employee> findEmployeesWithoutChief() {

        return employeeRepository.findEmployeesWithoutChief();

    }

    @Transactional
    public Employee delete(Long id) {

        if (!employeeRepository.findEmployeeWorkers(id).isEmpty()) return null;

        return employeeRepository.delete(id);

    }


    private boolean isChiefAbsentOrInSameOrganization(Employee employee) {

        if (employee.getChiefId() == null) return true;

        Employee chief = employeeRepository.findById(employee.getChiefId());

        return chief.getOrganizationId().equals(employee.getOrganizationId());

    }
}