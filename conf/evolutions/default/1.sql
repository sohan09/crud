# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table complex (
  id                        bigint not null,
  string_field              varchar(255),
  long_string_field         varchar(256),
  integer_field             integer,
  double_field              double,
  boolean_field             boolean,
  date_field                timestamp,
  constraint pk_complex primary key (id))
;

create table product (
  id                        bigint not null,
  name                      varchar(256),
  description               varchar(756),
  user_id                   bigint,
  created_date              timestamp,
  last_modified_date        timestamp,
  constraint pk_product primary key (id))
;

create table simple (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_simple primary key (id))
;

create table user (
  id                        bigint not null,
  first_name                varchar(50),
  last_name                 varchar(50),
  email                     varchar(100),
  created_date              timestamp,
  last_modified_date        timestamp,
  constraint pk_user primary key (id))
;

create sequence complex_seq;

create sequence product_seq;

create sequence simple_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists complex;

drop table if exists product;

drop table if exists simple;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists complex_seq;

drop sequence if exists product_seq;

drop sequence if exists simple_seq;

drop sequence if exists user_seq;

