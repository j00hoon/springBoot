CREATE SEQUENCE IF NOT EXISTS employee_seq;

CREATE TABLE IF NOT EXISTS employee (

employeeId BIGINT NOT NULL DEFAULT nextval('employee_seq') PRIMARY KEY,
email VARCHAR(100) NOT NULL,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL

);

CREATE SEQUENCE IF NOT EXISTS project_seq;

CREATE TABLE IF NOT EXISTS project (

projectId BIGINT NOT NULL DEFAULT nextval('project_seq') PRIMARY KEY,
project_name VARCHAR(100) NOT NULL,
project_stage VARCHAR(100) NOT NULL,
project_desc VARCHAR(500) NOT NULL

);


CREATE TABLE IF NOT EXISTS project_employee (

projectId BIGINT REFERENCES project, 
employeeId BIGINT REFERENCES employee

);