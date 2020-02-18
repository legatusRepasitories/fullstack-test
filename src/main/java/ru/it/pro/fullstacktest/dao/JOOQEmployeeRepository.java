package ru.it.pro.fullstacktest.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.jooq.db.tables.records.EmployeeRecord;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

import static ru.it.pro.fullstacktest.jooq.db.tables.Employee.EMPLOYEE;

@Component
public class JOOQEmployeeRepository implements EmployeeRepository {

    private DSLContext dslContext;

    @Autowired
    public JOOQEmployeeRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    @Transactional
    public Employee add(Employee employee) {

        EmployeeRecord record = dslContext.newRecord(EMPLOYEE, employee);
        record.store();

        return record.into(Employee.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Integer id) {
        EmployeeRecord record = dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .fetchOne();

        return record == null ? null : record.into(Employee.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Object findPageOfEmployees(Integer page) {
        return dslContext.selectFrom(EMPLOYEE)
                .orderBy(EMPLOYEE.ID)
                .limit(5)
                .offset(page * 5)
                .fetchInto(Employee.class);
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        EmployeeRecord record = dslContext.newRecord(EMPLOYEE, employee);
        dslContext.executeUpdate(record);

        return record.into(Employee.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findChiefWorkers(Integer id) {
        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.CHIEF_ID.eq(id))
                .fetchInto(Employee.class);

    }

    @Override
    @Transactional
    public Employee delete(Integer id) {

        Employee record = findById(id);
        dslContext.deleteFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .execute();

        return record;

    }
}
