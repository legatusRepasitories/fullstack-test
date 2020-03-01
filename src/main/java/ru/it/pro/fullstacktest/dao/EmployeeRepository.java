package ru.it.pro.fullstacktest.dao;

import org.springframework.data.domain.Pageable;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee add(Employee employee);

    Employee findById(Long id);

    String findPageOfEmployeesWithNameLike(Pageable pageable, String name);

    String findKeySetOfEmployeesWithNameLike(Long lastId, String name);

    List<Employee> findEmployeeWorkers(Long id);

    List<Employee> findEmployeesWithoutChief();

    List<Employee> findEmployeesOfOrganization(Long id);

    Employee update(Employee employee);

    Employee delete(Long id);
}
