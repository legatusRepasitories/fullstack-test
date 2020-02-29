DROP TABLE IF EXISTS employee, organization;

CREATE TABLE organization (
id                      BIGSERIAL   PRIMARY KEY,
name                    VARCHAR     NOT NULL,
head_organization_id    BIGINT                     REFERENCES organization(id)
);

CREATE TABLE employee (
id                      BIGSERIAL   PRIMARY KEY,
name                    VARCHAR     NOT NULL,
organization_id         BIGINT         NOT NULL    REFERENCES organization(id),
chief_id                BIGINT                     REFERENCES employee(id)
);