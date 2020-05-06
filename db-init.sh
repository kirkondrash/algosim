#!/bin/bash
set -e

psql -U postgres <<-EOSQL
    create database algosim;
    \connect algosim;
    create sequence orders_id_seq increment 40;
    create sequence order_triggers_id_seq increment 40;
    create table orders(
      id             integer     default nextval('orders_id_seq') primary key,
      pair           char(7)     not null,
      lot            numeric     not null,
      opening_price  numeric,
      closing_price  numeric,
      state          varchar(6)  not null,
      type           varchar(4)  not null
    );
    create table order_triggers(
      id             integer     default nextval('order_triggers_id_seq') primary key,
      order_id       serial      not null references orders,
      type           varchar(10) not null,
      trigger        numeric     not null
    );

    create table rates
    (
      pair           char(7)     not null unique,
      current_rate   numeric     not null,
      previous_rate  numeric     not null
    );

    create function is_level_crossed(
      trigger_level numeric,
      current_rate numeric,
      previous_rate numeric)
    returns boolean as $$
    begin
      return trigger_level<=current_rate and trigger_level>previous_rate or
             trigger_level>=current_rate and trigger_level<previous_rate;
    end; $$
    language plpgsql;
EOSQL
