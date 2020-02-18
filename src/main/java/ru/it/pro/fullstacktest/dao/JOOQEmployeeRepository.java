package ru.it.pro.fullstacktest.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.it.pro.fullstacktest.jooq.db.tables.records.EmployeeRecord;
import ru.it.pro.fullstacktest.model.Employee;

import static ru.it.pro.fullstacktest.jooq.db.tables.Employee.EMPLOYEE;

@Component
public class JOOQEmployeeRepository implements EmployeeRepository {

    private DSLContext dslContext;

    @Autowired
    public JOOQEmployeeRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Employee add(Employee employee) {

        EmployeeRecord record = dslContext.newRecord(EMPLOYEE, employee);
        record.store();

        return record.into(Employee.class);
    }
}
