package ru.it.pro.fullstacktest.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Organization {

    private Long id;
    private String name;
    private Long headOrganizationId;

}
