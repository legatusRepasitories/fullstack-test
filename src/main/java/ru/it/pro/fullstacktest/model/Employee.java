package ru.it.pro.fullstacktest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private Long organizationId;
    private Long chiefId;

}
