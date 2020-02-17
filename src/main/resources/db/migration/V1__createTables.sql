DROP TABLE IF EXISTS employee, organization;

CREATE TABLE organization (
id                      SERIAL    PRIMARY KEY,
name                    VARCHAR     NOT NULL,
head_organization_id    INT                     REFERENCES organization(id)
);

CREATE TABLE employee (
id                      SERIAL    PRIMARY KEY,
name                    VARCHAR     NOT NULL,
organization_id         INT         NOT NULL    REFERENCES organization(id),
chief_id                INT                     REFERENCES employee(id)
);