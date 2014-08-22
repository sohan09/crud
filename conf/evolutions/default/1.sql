# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table complex (
  id                        bigint auto_increment not null,
  string_field              varchar(255),
  long_string_field         varchar(256),
  integer_field             integer,
  double_field              double,
  boolean_field             tinyint(1) default 0,
  date_field                datetime,
  constraint pk_complex primary key (id))
;

create table product (
  id                        bigint auto_increment not null,
  name                      varchar(256),
  description               varchar(756),
  created_date              datetime,
  last_modified_date        datetime,
  constraint pk_product primary key (id))
;

create table simple (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_simple primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  first_name                varchar(50),
  last_name                 varchar(50),
  email                     varchar(100),
  created_date              datetime,
  last_modified_date        datetime,
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table complex;

drop table product;

drop table simple;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

