package ru.it.pro.fullstacktest.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.it.pro.fullstacktest.jooq.db.tables.records.OrganizationRecord;
import ru.it.pro.fullstacktest.model.Organization;


import java.util.ArrayList;
import java.util.List;

import static ru.it.pro.fullstacktest.jooq.db.tables.Organization.ORGANIZATION;


@Component
public class JOOQOrganizationOrganizationRepository implements OrganizationRepository {

    DSLContext dslContext;

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

        return convertQueryResultToModelObject(persisted);
    }


    @Override
    @Transactional(readOnly = true)
    public Organization findByName(String name) {
        OrganizationRecord record = dslContext.selectFrom(ORGANIZATION)
                .where(ORGANIZATION.NAME.equal(name))
                .fetchOne();

        return record == null ? null : convertQueryResultToModelObject(record);
    }


    @Override
    @Transactional(readOnly = true)
    public Organization findById(Integer id) {
        OrganizationRecord record = dslContext.selectFrom(ORGANIZATION)
                .where(ORGANIZATION.ID.equal(id))
                .fetchOne();

        return record == null ? null : convertQueryResultToModelObject(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAll() {
        List<Organization> organizationsEntries = new ArrayList<>();

        List<OrganizationRecord> queryResults = dslContext.selectFrom(ORGANIZATION).fetchInto(OrganizationRecord.class);

        for (OrganizationRecord record : queryResults) {
            Organization organization = convertQueryResultToModelObject(record);
            organizationsEntries.add(organization);
        }

        return organizationsEntries;
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

        return findById(entry.getId());
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


//    private OrganizationRecord createRecord(Organization organizationEntry) {
//
//        OrganizationRecord record = new OrganizationRecord();
//        record.setName(organizationEntry.getName());
//        record.setHeadOrganizationId(organizationEntry.getHeadOrganizationId());
//
//        return record;
//    }


    private Organization convertQueryResultToModelObject(OrganizationRecord queryResult) {
        return new Organization(queryResult.getId(), queryResult.getName(), queryResult.getHeadOrganizationId());
    }
}
