# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table complex (
  key                       bigint not null,
  string_field              varchar(255),
  long_string_field         varchar(256),
  integer_field             integer,
  double_field              float,
  boolean_field             boolean,
  date_field                timestamp,
  constraint pk_complex primary key (key))
;

create table simple (
  key                       bigint not null,
  name                      varchar(255),
  constraint pk_simple primary key (key))
;

create sequence complex_seq;

create sequence simple_seq;




# --- !Downs

drop table if exists complex cascade;

drop table if exists simple cascade;

drop sequence if exists complex_seq;

drop sequence if exists simple_seq;

