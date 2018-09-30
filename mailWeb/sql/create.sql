create database if not exists hncu character set utf8;

use hncu;

create table user(
	id varchar(32) primary key,
	name varchar(30),
	pwd varchar(30),
	email varchar(50),
	acode varchar(32)
);
ALTER TABLE USER ADD CONSTRAINT uk_user UNIQUE (NAME);