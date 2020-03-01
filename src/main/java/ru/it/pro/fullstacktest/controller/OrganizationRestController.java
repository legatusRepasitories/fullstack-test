package ru.it.pro.fullstacktest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.it.pro.fullstacktest.model.Organization;
import ru.it.pro.fullstacktest.service.OrganizationService;

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
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findPageOfOrganizationsOffset(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "name", defaultValue = "") String organizationName) {

        return organizationService.findPageOfOrganizations(pageable, organizationName);
    }


    @GetMapping(value = "/keySet", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findPageOfOrganizationsKeySet(
            @RequestParam(defaultValue = "0") Long lastId,
            @RequestParam(value = "name", defaultValue = "") String organizationName) {

        return organizationService.findPageOfOrganizationsKeySet(lastId, organizationName);
    }

    @GetMapping(value = "/tree")
    public List<Organization> findOrganizationsBases() {

        return organizationService.findOrganizationsBases();
    }

    @GetMapping(value = "/all")
    public List<Organization> findAll() {
        return organizationService.findAll();
    }


    @GetMapping(path = "/{id}")
    public Organization findOrganizationById(@PathVariable Long id) {
        return organizationService.findById(id);
    }

    @GetMapping(path = "/name/{name}")
    public Organization findOrganizationByName(@PathVariable String name) {
        return organizationService.findByName(name);

    }

    @GetMapping(path = "/{id}/child")
    public List<Organization> findAffiliatedOrganizations(@PathVariable Long id) {
        return organizationService.findAffiliatedOrganizations(id);
    }

    @DeleteMapping(path = "/{id}")
    public Organization deleteOrganizationById(@PathVariable Long id) {
        return organizationService.delete(id);

    }

    @PutMapping
    public Organization updateOrganization(@RequestBody Organization organization) {
        return organizationService.update(organization);

    }
}
