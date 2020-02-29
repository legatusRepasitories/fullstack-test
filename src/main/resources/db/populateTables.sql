INSERT INTO organization (id, name) VALUES
    (100, 'Level 1 Organization');

INSERT INTO organization VALUES
    (200, 'Level 2 Organization 1', 100),
    (201, 'Level 2 Organization 2', 100),
    (300, 'Level 3 Organization 1', 200),
    (301, 'Level 3 Organization 2', 200),
    (302, 'Level 3 Organization 3', 201);

INSERT INTO employee (id, name, organization_id) VALUES
    (1000, 'Head of organization 1', 100),
    (1001, 'Head of organization 2', 200),
    (1002, 'Head of organization 3', 201),
    (1003, 'Head of organization 4', 300),
    (1004, 'Head of organization 5', 301),
    (1005, 'Head of organization 6', 302);

INSERT INTO employee VALUES
    (10000, 'Employee 1', 100, 1000),
    (10001, 'Employee 2', 100, 1000),
    (10002, 'Employee 3', 200, 1001),
    (10003, 'Employee 4', 201, 1002),
    (10004, 'Employee 5', 300, 1003),
    (10005, 'Employee 6', 301, 1004),
    (10006, 'Employee 7', 301, 1004),
    (10007, 'Employee 8', 302, 1005);