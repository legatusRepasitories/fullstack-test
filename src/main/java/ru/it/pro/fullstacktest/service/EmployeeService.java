package ru.it.pro.fullstacktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.it.pro.fullstacktest.dao.EmployeeRepository;
import ru.it.pro.fullstacktest.jooq.db.tables.records.EmployeeRecord;
import ru.it.pro.fullstacktest.model.Employee;

import static ru.it.pro.fullstacktest.jooq.db.tables.Employee.EMPLOYEE;

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

    public Employee update(Employee employee) {
        return isChiefAbsentOrInSameOrganization(employee) ? repository.update(employee) : null;
    }

    private boolean isChiefAbsentOrInSameOrganization(Employee employee) {
        if (employee.getChiefId() == null) return true;

        Employee chief = repository.findById(employee.getChiefId());
        
        return chief.getOrganizationId().equals(employee.getOrganizationId());
    }
}
