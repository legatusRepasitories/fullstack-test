package ru.it.pro.fullstacktest.dao;

import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee add(Employee employee);

    Employee findById(Integer id);

    Object findPageOfEmployees(Integer page);

    Object findPageOfEmployeesWithNameLike(Integer page, String name);

    Employee update(Employee employee);

    List<Employee> findChiefWorkers(Integer id);

    Employee delete(Integer id);
}
