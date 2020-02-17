package ru.it.pro.fullstacktest.model;

public class Organization {

    private Integer id;
    private String name;
    private Integer headOrganizationId;

    public Organization() {

    }

    public Organization(String name, Integer headOrganizationId) {
        this.name = name;
        this.headOrganizationId = headOrganizationId;
    }

    public Organization(Integer id, String name, Integer headOrganizationId) {
        this.id = id;
        this.name = name;
        this.headOrganizationId = headOrganizationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeadOrganizationId() {
        return headOrganizationId;
    }

    public void setHeadOrganizationId(Integer headOrganizationId) {
        this.headOrganizationId = headOrganizationId;
    }
}
