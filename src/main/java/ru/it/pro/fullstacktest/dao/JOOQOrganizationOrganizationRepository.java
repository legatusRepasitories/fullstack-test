package ru.it.pro.fullstacktest.dao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.jooq.AggregateFunction;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.jooq.db.tables.records.OrganizationRecord;
import ru.it.pro.fullstacktest.model.Employee;
import ru.it.pro.fullstacktest.model.Organization;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jooq.JSONFormat.DEFAULT_FOR_RECORDS;
import static org.jooq.JSONFormat.RecordFormat.OBJECT;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.name;
import static ru.it.pro.fullstacktest.jooq.db.tables.Employee.EMPLOYEE;
import static ru.it.pro.fullstacktest.jooq.db.tables.Organization.ORGANIZATION;


@Component
public class JOOQOrganizationOrganizationRepository implements OrganizationRepository {

    DSLContext dslContext;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public JOOQOrganizationOrganizationRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    @Transactional
    public Organization add(Organization organizationEntry) {

//        OrganizationRecord persisted = dslContext.insertInto(ORGANIZATION)
//                .set(createRecord(organizationEntry))
//                .returning()
//                .fetchOne();

        OrganizationRecord persisted = dslContext.newRecord(ORGANIZATION);
        persisted.setName(organizationEntry.getName());
        persisted.setHeadOrganizationId(organizationEntry.getHeadOrganizationId());
        persisted.store();

        return persisted.into(Organization.class);
    }


    @Override
    @Transactional(readOnly = true)
    public Organization findByName(String name) {
        OrganizationRecord record = dslContext.selectFrom(ORGANIZATION)
                .where(ORGANIZATION.NAME.equal(name))
                .fetchOne();

        return record == null ? null : record.into(Organization.class);
    }


    @Override
    @Transactional(readOnly = true)
    public Organization findById(Integer id) {
        OrganizationRecord record = dslContext.selectFrom(ORGANIZATION)
                .where(ORGANIZATION.ID.equal(id))
                .fetchOne();

        return record == null ? null : record.into(Organization.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAffiliatedOrganizations(Integer id) {

        return dslContext
                .selectFrom(ORGANIZATION)
                .where(ORGANIZATION.HEAD_ORGANIZATION_ID.eq(id))
                .fetchInto(Organization.class);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAll() {
        List<Organization> organizationsEntries = new ArrayList<>();

        return dslContext.selectFrom(ORGANIZATION).fetchInto(Organization.class);

    }

    @Override
    @Transactional(readOnly = true)
    public Object findPageOfOrganizationsWithNameLike(Pageable pageable, String organizationName) {


        String json = dslContext.select(ORGANIZATION.ID.as("id"), ORGANIZATION.NAME.as("name"), count(EMPLOYEE.ID).as("employeeCount"))
                .from(ORGANIZATION)
                .leftJoin(EMPLOYEE).on(EMPLOYEE.ORGANIZATION_ID.eq(ORGANIZATION.ID))
                .where(ORGANIZATION.NAME.contains(organizationName))
                .groupBy(ORGANIZATION.ID)
                .orderBy(ORGANIZATION.ID)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch().formatJSON(DEFAULT_FOR_RECORDS.recordFormat(OBJECT));

        long totalCount = findCountByContainsExpression(organizationName);
        int totalPages = (int) Math.ceil((double) totalCount/pageable.getPageSize());
        StringBuilder sb = new StringBuilder();
        sb.append("{\"content\": [").append(json.substring(1, json.length() - 1))
                .append("], \"number\": ").append(pageable.getPageNumber())
                .append(",\"numberOfElements\": ").append(pageable.getPageSize())
                .append(",\"totalElements\": ").append(totalCount)
                .append(",\"totalPages\": ").append(totalPages)
                .append("}");
        return sb.toString();

    }



    @Override
    @Transactional(readOnly = true)
    public Page<Record3<Integer, String, Integer>> findPageOfOrganizationsWithNameLike(String organizationName, Pageable pageable) {

        Result<Record3<Integer, String, Integer>> organizations =
        dslContext
                .select(ORGANIZATION.ID.as("id"), ORGANIZATION.NAME.as("name"), count(EMPLOYEE.ID).as("employeeCount"))
                .from(ORGANIZATION)
                .leftJoin(EMPLOYEE).on(EMPLOYEE.ORGANIZATION_ID.eq(ORGANIZATION.ID))
                .where(ORGANIZATION.NAME.contains(organizationName))
                .groupBy(ORGANIZATION.ID)
                .orderBy(ORGANIZATION.ID)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        long totalCount = findCountByContainsExpression(organizationName);

        return new PageImpl<>(organizations, pageable, totalCount);

    }


    private long findCountByContainsExpression(String expression) {
        return dslContext.fetchCount(
                dslContext.selectFrom(ORGANIZATION)
                        .where(ORGANIZATION.NAME.contains(expression))
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<Organization> findPageOfOrganizationsKeySet(Integer lastId) {

        return dslContext
                .selectFrom(ORGANIZATION)
                .orderBy(ORGANIZATION.ID)
                .seek(lastId)
                .limit(5)
                .fetchInto(Organization.class);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findPageOfOrganizationsKeySetWithNameLike(Integer lastId, String organizationName) {

        return dslContext
                .selectFrom(ORGANIZATION)
                .where(ORGANIZATION.NAME.contains(organizationName))
                .orderBy(ORGANIZATION.ID)
                .seek(lastId)
                .limit(5)
                .fetchInto(Organization.class);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findOrganizationsBases() {

        return dslContext
                .selectFrom(ORGANIZATION)
                .where(ORGANIZATION.HEAD_ORGANIZATION_ID.isNull())
                .fetchInto(Organization.class);

    }

    @Override
    @Transactional
    public Organization update(Organization entry) {
//        int updatedRecordCount = dslContext.update(ORGANIZATION)
//                .set(ORGANIZATION.NAME, entry.getName())
//                .set(ORGANIZATION.HEAD_ORGANIZATION_ID, entry.getHeadOrganizationId())
//                .where(ORGANIZATION.ID.equal(entry.getId()))
//                .execute();

//        OrganizationRecord updateRecord = dslContext.fetchOne(ORGANIZATION, ORGANIZATION.ID.eq(entry.getId()));
//        updateRecord.setName(entry.getName());
//        updateRecord.setHeadOrganizationId(entry.getHeadOrganizationId());
//        updateRecord.store();

        OrganizationRecord updateRecord = dslContext.newRecord(ORGANIZATION, entry);

        dslContext.executeUpdate(updateRecord);

        return updateRecord.into(Organization.class);
    }


    @Override
    @Transactional
    public Organization delete(Integer id) {
        Organization deleted = findById(id);

        int deletedRecordCount = dslContext.deleteFrom(ORGANIZATION)
                .where(ORGANIZATION.ID.equal(id))
                .execute();

        return deleted;
    }
}
