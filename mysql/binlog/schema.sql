create database if not exists test;

use test;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user
(
    id       INTEGER AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO t_user (username, password)
VALUES ('test-1', '1'),
       ('test-2', '2'),
       ('test-3', '3');

INSERT INTO t_user (username, password)
VALUES ('test-4', '4');