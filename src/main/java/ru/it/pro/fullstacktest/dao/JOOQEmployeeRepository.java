package ru.it.pro.fullstacktest.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.jooq.db.tables.records.EmployeeRecord;
import ru.it.pro.fullstacktest.jooq.db.tables.records.OrganizationRecord;
import ru.it.pro.fullstacktest.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.JSONFormat.DEFAULT_FOR_RECORDS;
import static org.jooq.JSONFormat.RecordFormat.ARRAY;
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
    public Object findPageOfEmployeesWithNameLike(Pageable pageable, String name) {

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


        String json = dslContext
                .select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName")).from(e)
                .join(o).on(eOrganizationId.eq(oId))
                .leftJoin(c).on(cId.eq(eChiefId))
                .where(eName.contains(name)).or(cName.contains(name))
                .orderBy(eId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .formatJSON(DEFAULT_FOR_RECORDS.recordFormat(OBJECT));

        long totalCount = dslContext.fetchCount(
                dslContext.select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName")).from(e)
                        .join(o).on(eOrganizationId.eq(oId))
                        .leftJoin(c).on(cId.eq(eChiefId))
                        .where(eName.contains(name)).or(cName.contains(name))
        );

        StringBuilder sb = new StringBuilder();
        sb.append("{\"content\": [").append(json.substring(1, json.length() - 1))
                .append("], \"number\": ").append(pageable.getPageNumber())
                .append(",\"numberOfElements\": ").append(pageable.getPageSize())
                .append(",\"totalElements\": ").append(totalCount)
                .append(",\"totalPages\": ").append((int) Math.ceil((double) totalCount/pageable.getPageSize()))
                .append("}");

        return sb.toString();
//        return "{\"content\": [" + substring + "], \"page\": " + pageable.getPageNumber() + "}";
//        System.out.println(substring);
//        return json + ",\"page\": " + pageable.getPageNumber();

    }

    @Autowired
    ObjectMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<Record4<Integer, String, String, String>> findPageOfOrganizationsWithNameLike(String name, Pageable pageable) {

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


        Result<Record4<Integer, String, String, String>> employees = dslContext
                .select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName")).from(e)
                .join(o).on(eOrganizationId.eq(oId))
                .leftJoin(c).on(cId.eq(eChiefId))
                .where(eName.contains(name)).or(cName.contains(name))
                .orderBy(eId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        SerializationConfig serializationConfig = mapper.getSerializationConfig();
        long totalCount = dslContext.fetchCount(
                dslContext.select(eId.as("id"), eName.as("name"), cName.as("chiefName"), oName.as("organizationName")).from(e)
                        .join(o).on(eOrganizationId.eq(oId))
                        .leftJoin(c).on(cId.eq(eChiefId))
                        .where(eName.contains(name)).or(cName.contains(name))
        );


        List<String> map = employees.map(record -> record.formatJSON(DEFAULT_FOR_RECORDS.recordFormat(OBJECT)));
        List<String> map1 = employees.map(record -> record.formatJSON(DEFAULT_FOR_RECORDS.recordFormat(ARRAY)));
        return new PageImpl<>(employees, pageable, totalCount);
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
