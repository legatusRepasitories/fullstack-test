package ru.it.pro.fullstacktest.dao;

import org.jooq.Record4;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee add(Employee employee);

    Employee findById(Integer id);

    Object findPageOfEmployeesWithNameLike(Pageable pageable, String name);

    Page<Record4<Integer, String, String, String>> findPageOfOrganizationsWithNameLike(String name, Pageable pageable);

    Employee update(Employee employee);

    List<Employee> findEmployeeWorkers(Integer id);

    List<Employee> findEmployeesWithoutChief();

    List<Employee> findEmployeeOfOrganization(Integer id);

    Employee delete(Integer id);
}
