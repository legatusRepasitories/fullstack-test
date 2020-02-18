package ru.it.pro.fullstacktest.dao;

import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.jooq.db.tables.records.EmployeeRecord;
import ru.it.pro.fullstacktest.jooq.db.tables.records.OrganizationRecord;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

import static org.jooq.JSONFormat.DEFAULT_FOR_RECORDS;
import static org.jooq.JSONFormat.RecordFormat.OBJECT;
import static ru.it.pro.fullstacktest.jooq.db.tables.Employee.EMPLOYEE;
import static ru.it.pro.fullstacktest.jooq.db.tables.Organization.ORGANIZATION;

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
    public Object findPageOfEmployeesWithNameLike(Integer page, String name) {

        Table<EmployeeRecord> e = EMPLOYEE.as("e");
        Field<Integer> eId = e.field(EMPLOYEE.ID);
        Field<String> eName = e.field(EMPLOYEE.NAME);
        Field<Integer> eChiefId = e.field(EMPLOYEE.CHIEF_ID);
        Field<Integer> eOrganizationId = e.field(EMPLOYEE.ORGANIZATION_ID);

        Table<EmployeeRecord> c = EMPLOYEE.as("c");
        Field<Integer> cId = c.field(EMPLOYEE.ID);
        Field<String> cName = c.field(EMPLOYEE.NAME);

        Table<OrganizationRecord> o = ORGANIZATION.as("o");
        Field<Integer> oId = o.field(ORGANIZATION.ID);
        Field<String> oName = o.field(ORGANIZATION.NAME);


        return dslContext
                .select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName")).from(e)
                .join(o).on(eOrganizationId.eq(oId))
                .leftJoin(c).on(cId.eq(eChiefId))
                .where(eName.contains(name)).or(cName.contains(name))
                .orderBy(eId)
                .offset(page * 5)
                .limit(5)
                .fetch()
                .formatJSON(DEFAULT_FOR_RECORDS.recordFormat(OBJECT));

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
    public List<Employee> findEmployeeWorkers(Integer id) {
        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.CHIEF_ID.eq(id))
                .fetchInto(Employee.class);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findEmployeesWithoutChief() {
        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.CHIEF_ID.isNull())
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
