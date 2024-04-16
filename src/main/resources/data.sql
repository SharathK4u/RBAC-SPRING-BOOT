
INSERT INTO PRIVILEGES VALUES (1, 'Read Database', 'Read Database Privilege', 'Read', 'Database');
INSERT INTO PRIVILEGES VALUES (2, 'Write Database', 'Write Database Privilege', 'Write', 'Database');
INSERT INTO PRIVILEGES VALUES (3, 'Delete Database', 'Delete Database Privilege', 'Delete', 'Database');
INSERT INTO PRIVILEGES VALUES (4, 'Grant Database', 'Grant Database Privilege', 'Grant', 'Database');
INSERT INTO PRIVILEGES VALUES (5, 'Revoke Database', 'Revoke Database Privilege', 'Revoke', 'Database');

INSERT INTO ROLES VALUES (1, 'Admin', 'Super User having all privileges');
INSERT INTO ROLES VALUES (2, 'Supervisor', 'User having Grant/Revoke Access');
INSERT INTO ROLES VALUES (3, 'Worker', 'Worker have basic Read/Write privilege');

INSERT INTO ROLE_PRIVILEGE VALUES(1,1);
INSERT INTO ROLE_PRIVILEGE VALUES(1,2);
INSERT INTO ROLE_PRIVILEGE VALUES(1,3);
INSERT INTO ROLE_PRIVILEGE VALUES(1,4);
INSERT INTO ROLE_PRIVILEGE VALUES(1,5);
INSERT INTO ROLE_PRIVILEGE VALUES(2,4);
INSERT INTO ROLE_PRIVILEGE VALUES(2,5);
INSERT INTO ROLE_PRIVILEGE VALUES(3,1);
INSERT INTO ROLE_PRIVILEGE VALUES(3,2);

INSERT INTO USERS VALUES(1, 38, 'Sharath', 'Damodaran', 1);