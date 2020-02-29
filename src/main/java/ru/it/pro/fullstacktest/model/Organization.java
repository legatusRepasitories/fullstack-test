package ru.it.pro.fullstacktest.model;

public class Organization {

    private Long id;
    private String name;
    private Long headOrganizationId;

    public Organization() {

    }

    public Organization(String name, Long headOrganizationId) {
        this.name = name;
        this.headOrganizationId = headOrganizationId;
    }

    public Organization(Long id, String name, Long headOrganizationId) {
        this.id = id;
        this.name = name;
        this.headOrganizationId = headOrganizationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHeadOrganizationId() {
        return headOrganizationId;
    }

    public void setHeadOrganizationId(Long headOrganizationId) {
        this.headOrganizationId = headOrganizationId;
    }
}
