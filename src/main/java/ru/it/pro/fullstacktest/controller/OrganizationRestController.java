package ru.it.pro.fullstacktest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSONFormat;
import org.jooq.Record3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.it.pro.fullstacktest.jooq.db.tables.records.OrganizationRecord;
import ru.it.pro.fullstacktest.model.Organization;
import ru.it.pro.fullstacktest.service.OrganizationService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/organization")
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


    //offset pagination is bad https://blog.jooq.org/tag/offset-pagination/
    @GetMapping(value = "/list")
    public Object findPageOfOrganizationsOffset(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(value = "name", defaultValue = "") String organizationName) {

        return organizationService.findPageOfOrganizations(page, organizationName);
    }

    @GetMapping(value = "/springList")
    public Page<Record3<Integer, String, Integer>> findPageOfOrganizationsOffset(
            @RequestParam(value = "name", defaultValue = "") String organizationName,
            @PageableDefault(size = 5) Pageable pageable) {


        return organizationService.findPageOfOrganizations(organizationName, pageable);
    }

    //TODO: keySet pagination
    @GetMapping(value = "/keySet")
    public Object findPageOfOrganizationsKeySet(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(value = "name", defaultValue = "") String organizationName) {

        return organizationService.findPageOfOrganizationsKeySet(lastId, organizationName);
    }

    @GetMapping(value = "/tree")
    public List<Organization> findOrganizationsBases() {

        return organizationService.findOrganizationsBases();
    }


    @GetMapping(path = "/{id}")
    public Organization findOrganizationById(@PathVariable Integer id) {
        return organizationService.findById(id);
    }

    @GetMapping(path = "/name/{name}")
    public Organization findOrganizationByName(@PathVariable String name) {
        return organizationService.findByName(name);

    }

    @GetMapping(path = "/{id}/child")
    public List<Organization> findAffiliatedOrganizations(@PathVariable Integer id) {
        return organizationService.findAffiliatedOrganizations(id);
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
