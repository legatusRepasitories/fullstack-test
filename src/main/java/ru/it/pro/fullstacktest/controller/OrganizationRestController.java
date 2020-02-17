package ru.it.pro.fullstacktest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSONFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.it.pro.fullstacktest.jooq.db.tables.records.OrganizationRecord;
import ru.it.pro.fullstacktest.model.Organization;
import ru.it.pro.fullstacktest.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationRestController {

    private OrganizationService organizationService;

    @Autowired
    public OrganizationRestController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.add(organization);
    }

    @GetMapping(value = "/list")
    public List<Organization> findAll() {
        return organizationService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Organization findOrganizationById(@PathVariable Integer id) {
        return organizationService.findById(id);
    }

    @GetMapping(path = "/name/{name}")
    public Organization findOrganizationByName(@PathVariable String name) {
        return organizationService.findByName(name);

    }

    @DeleteMapping(path = "/{id}")
    public Organization deleteOrganizationById(@PathVariable Integer id) {
        return organizationService.delete(id);

    }

    @PutMapping
    public Organization updateOrganization(@RequestBody Organization organization) {
        return organizationService.update(organization);

    }
}
