#!/bin/bash
set -e

psql -U postgres <<-EOSQL
    CREATE DATABASE algosim;
    \connect algosim;
    create sequence orders_id_seq increment 40;
    create sequence order_triggers_id_seq increment 40;
    create table orders(
      id             integer    default nextval('orders_id_seq') primary key,
      pair           char(7)    not null,
      lot            numeric    not null,
      opening_price  numeric,
      closing_price  numeric,
      state          varchar(6) not null,
      type           varchar(4) not null
    );
      create table order_triggers(
      id             integer    default nextval('order_triggers_id_seq') primary key,
      order_id       serial      not null references orders,
      type           varchar(10) not null,
      trigger        numeric     not null
    );
EOSQL
