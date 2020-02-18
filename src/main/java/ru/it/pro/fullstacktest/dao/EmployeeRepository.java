package ru.it.pro.fullstacktest.dao;

import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee add(Employee employee);

    Employee findById(Integer id);

    Employee update(Employee employee);

    List<Employee> findChiefWorkers(Integer id);
}
