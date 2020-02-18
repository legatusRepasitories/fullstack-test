package ru.it.pro.fullstacktest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.it.pro.fullstacktest.model.Employee;
import ru.it.pro.fullstacktest.service.EmployeeService;

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

}
