DROP TABLE IF EXISTS ASSIGNMENT;

CREATE TABLE ASSIGNMENT(
    aid LONG PRIMARY KEY,
    aname VARCHAR (25) NOT NULL,
    astudypoints int NOT NULL
);


DROP TABLE IF EXISTS ASSIGNMENT_STUDENT;

CREATE TABLE ASSIGNMENT_STUDENT(
    asid LONG PRIMARY KEY,
    aid LONG NOT NULL,
    smail VARCHAR(25) NOT NULL
);