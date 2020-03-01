package ru.it.pro.fullstacktest.dao;


import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.jooq.db.tables.records.EmployeeRecord;
import ru.it.pro.fullstacktest.jooq.db.tables.records.OrganizationRecord;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;

import static org.jooq.JSONFormat.DEFAULT_FOR_RECORDS;
import static org.jooq.JSONFormat.RecordFormat.OBJECT;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;
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
    @Transactional(propagation = MANDATORY)
    public Employee add(Employee employee) {

        EmployeeRecord record = dslContext.newRecord(EMPLOYEE, employee);
        record.store();

        return record.into(Employee.class);

    }


    @Override
    @Transactional(propagation = MANDATORY)
    public Employee findById(Long id) {

        EmployeeRecord record = dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .fetchOne();

        return record == null ? null : record.into(Employee.class);

    }


    @Override
    @Transactional(propagation = MANDATORY)
    public List<Employee> findEmployeesOfOrganization(Long id) {

        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.ORGANIZATION_ID.eq(id))
                .fetchInto(Employee.class);

    }


    @Override
    @Transactional(propagation = MANDATORY)
    public String findPageOfEmployeesWithNameLike(Pageable pageable, String name) {

        Table<EmployeeRecord> e = EMPLOYEE.as("e");
        Field<Long> eId = e.field(EMPLOYEE.ID);
        Field<String> eName = e.field(EMPLOYEE.NAME);
        Field<Long> eChiefId = e.field(EMPLOYEE.CHIEF_ID);
        Field<Long> eOrganizationId = e.field(EMPLOYEE.ORGANIZATION_ID);

        Table<EmployeeRecord> c = EMPLOYEE.as("c");
        Field<Long> cId = c.field(EMPLOYEE.ID);
        Field<String> cName = c.field(EMPLOYEE.NAME);

        Table<OrganizationRecord> o = ORGANIZATION.as("o");
        Field<Long> oId = o.field(ORGANIZATION.ID);
        Field<String> oName = o.field(ORGANIZATION.NAME);

        String json = dslContext
                .select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName")).from(e)
                .join(o).on(eOrganizationId.eq(oId))
                .leftJoin(c).on(cId.eq(eChiefId))
                .where(eName.contains(name)).or(oName.contains(name))
                .orderBy(eId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .formatJSON(DEFAULT_FOR_RECORDS.recordFormat(OBJECT));

        long totalCount = dslContext.fetchCount(
                dslContext.select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName"))
                        .from(e).join(o).on(eOrganizationId.eq(oId))
                        .leftJoin(c).on(cId.eq(eChiefId))
                        .where(eName.contains(name)).or(oName.contains(name))
        );

        return String.format("{\"content\": [%s], \"number\": %d, \"numberOfElements\": %d, \"totalElements\": %d, \"totalPages\": %d}",
                json.substring(1, json.length() - 1),
                pageable.getPageNumber(),
                pageable.getPageSize(),
                totalCount,
                (int) Math.ceil((double) totalCount / pageable.getPageSize()));

    }


    @Override
    @Transactional(propagation = MANDATORY)
    public String findKeySetOfEmployeesWithNameLike(Long lastId, String name) {
        Table<EmployeeRecord> e = EMPLOYEE.as("e");
        Field<Long> eId = e.field(EMPLOYEE.ID);
        Field<String> eName = e.field(EMPLOYEE.NAME);
        Field<Long> eChiefId = e.field(EMPLOYEE.CHIEF_ID);
        Field<Long> eOrganizationId = e.field(EMPLOYEE.ORGANIZATION_ID);

        Table<EmployeeRecord> c = EMPLOYEE.as("c");
        Field<Long> cId = c.field(EMPLOYEE.ID);
        Field<String> cName = c.field(EMPLOYEE.NAME);

        Table<OrganizationRecord> o = ORGANIZATION.as("o");
        Field<Long> oId = o.field(ORGANIZATION.ID);
        Field<String> oName = o.field(ORGANIZATION.NAME);

        return  dslContext
                .select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName")).from(e)
                .join(o).on(eOrganizationId.eq(oId))
                .leftJoin(c).on(cId.eq(eChiefId))
                .where(eName.contains(name)).or(oName.contains(name))
                .orderBy(eId)
                .seek(lastId)
                .limit(5)
                .fetch()
                .formatJSON(DEFAULT_FOR_RECORDS.recordFormat(OBJECT));
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public Employee update(Employee employee) {

        EmployeeRecord record = dslContext.newRecord(EMPLOYEE, employee);
        dslContext.executeUpdate(record);

        return record.into(Employee.class);

    }

    @Override
    @Transactional(propagation = MANDATORY)
    public List<Employee> findEmployeeWorkers(Long id) {

        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.CHIEF_ID.eq(id))
                .fetchInto(Employee.class);

    }

    @Override
    @Transactional(propagation = MANDATORY)
    public List<Employee> findEmployeesWithoutChief() {

        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.CHIEF_ID.isNull())
                .fetchInto(Employee.class);

    }

    @Override
    @Transactional(propagation = MANDATORY)
    public Employee delete(Long id) {

        Employee record = findById(id);
        dslContext.deleteFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .execute();

        return record;

    }
}
