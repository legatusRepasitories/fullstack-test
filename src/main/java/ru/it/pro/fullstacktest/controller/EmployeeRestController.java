package ru.it.pro.fullstacktest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.it.pro.fullstacktest.model.Employee;
import ru.it.pro.fullstacktest.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.add(employee);
    }

    @GetMapping(path = "/{id}")
    public Employee findById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @GetMapping(path = "/{id}/workers")
    public List<Employee> findChiefWorkers(@PathVariable Integer id) {
        return employeeService.findChiefWorkers(id);
    }

    @PutMapping
    public Employee update(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @DeleteMapping(path = "/{id}")
    public Employee delete(@PathVariable Integer id) {
        return employeeService.delete(id);
    }
}
