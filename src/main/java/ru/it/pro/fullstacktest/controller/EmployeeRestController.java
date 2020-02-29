package ru.it.pro.fullstacktest.controller;

import org.jooq.Record4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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

    @GetMapping(path = "/{id}/child")
    public List<Employee> findChiefWorkers(@PathVariable Integer id) {
        return employeeService.findChiefWorkers(id);
    }

    @GetMapping(path = "/tree")
    public List<Employee> findEmployeesWithoutChief() {
        return employeeService.findEmployeesWithoutChief();
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findPageOfEmployees(@PageableDefault(size = 5) Pageable pageable,
                                      @RequestParam(defaultValue = "") String name) {

        return employeeService.findPageOfEmployees(pageable, name.strip());
    }

    @GetMapping(path = "/organization/{id}")
    public List<Employee> findEmployeeOfOrganization(@PathVariable Integer id) {
        return employeeService.findEmployeeOfOrganization(id);
    }

    @GetMapping(path = "/springList")
    public Page<Record4<Integer, String, String, String>> findPageOfOrganizationsWithNameLike(@RequestParam(defaultValue = "") String name,
                                                                                              @PageableDefault(size = 5) Pageable pageable) {
        return employeeService.findPageOfOrganizationsWithNameLike(name, pageable);
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
