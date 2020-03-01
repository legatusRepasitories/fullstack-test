CREATE ROLE full_stack_test_admin WITH
    LOGIN
    NOSUPERUSER
    CREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT -1
    PASSWORD 'full_stack_test_admin';

CREATE DATABASE test_db
    WITH
    OWNER = full_stack_test_admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;