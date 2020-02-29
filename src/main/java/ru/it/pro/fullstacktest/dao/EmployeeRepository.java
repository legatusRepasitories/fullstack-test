package ru.it.pro.fullstacktest.dao;

import org.jooq.Record4;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee add(Employee employee);

    Employee findById(Long id);

    Object findPageOfEmployeesWithNameLike(Pageable pageable, String name);

    Page<Record4<Long, String, String, String>> findPageOfOrganizationsWithNameLike(String name, Pageable pageable);

    Employee update(Employee employee);

    List<Employee> findEmployeeWorkers(Long id);

    List<Employee> findEmployeesWithoutChief();

    List<Employee> findEmployeeOfOrganization(Long id);

    Employee delete(Long id);
}
