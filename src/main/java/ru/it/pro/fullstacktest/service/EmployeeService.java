package ru.it.pro.fullstacktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.it.pro.fullstacktest.dao.EmployeeRepository;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee add(Employee employee) {
        return isChiefAbsentOrInSameOrganization(employee) ? repository.add(employee) : null;
    }

    public Employee findById(Integer id) {
        return repository.findById(id);
    }

    public Object findPageOfEmployees(Integer page, String name) {

        return name == null ?
                repository.findPageOfEmployees(page) : repository.findPageOfEmployeesWithNameLike(page, name);
    }

    public Employee update(Employee employee) {
        return isChiefAbsentOrInSameOrganization(employee) ? repository.update(employee) : null;
    }

    public List<Employee> findChiefWorkers(Integer id) {
        return repository.findEmployeeWorkers(id);
    }

    public List<Employee> findEmployeesWithoutChief() {
        return repository.findEmployeesWithoutChief();
    }

    public Employee delete(Integer id) {
        if (!repository.findEmployeeWorkers(id).isEmpty()) return null;

        return repository.delete(id);
    }

    private boolean isChiefAbsentOrInSameOrganization(Employee employee) {
        if (employee.getChiefId() == null) return true;

        Employee chief = repository.findById(employee.getChiefId());
        
        return chief.getOrganizationId().equals(employee.getOrganizationId());
    }
}
