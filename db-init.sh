#!/bin/bash
set -e

psql -U postgres <<-EOSQL
    create database algosim;
    \connect algosim;
    create table statuses(
      id             serial      primary key,
      algo_id        varchar     not null unique,
      status         varchar     not null,
      error_trace    varchar,
      winloss        varchar
    );

    create table sources(
      id             serial      primary key,
      algo_id        varchar     not null unique,
      path           varchar
    );

    create table artifacts(
      id             serial      primary key,
      algo_id        varchar     not null unique,
      path           varchar
    );

    create table bhacklogins(
      login          varchar     not null,
      password       varchar     not null,
      permit         varchar     not null
    );
    insert into bhacklogins values ('user','password','user'),('admin','admin','admin');
    create table orders(
      id             serial      primary key,
      algo_id        varchar     not null,
      pair           char(7)     not null,
      lot            numeric     not null,
      opening_price  numeric,
      closing_price  numeric,
      state          varchar(6)  not null,
      type           varchar(4)  not null
    );
    create table order_triggers(
      id             serial      primary key,
      order_id       serial      not null references orders(id) on delete cascade,
      type           varchar(10) not null,
      trigger        numeric     not null
    );
    create function is_level_crossed(
      trigger_level numeric,
      current_rate numeric,
      previous_rate numeric)
    returns boolean as \$\$
    begin
      return trigger_level<=current_rate and trigger_level>previous_rate or
             trigger_level>=current_rate and trigger_level<previous_rate;
    end; \$\$
    language plpgsql;
EOSQL
