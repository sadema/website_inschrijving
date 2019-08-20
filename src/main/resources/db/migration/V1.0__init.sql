create sequence hibernate_sequence;

create table product (
  id bigint not null,
  productid binary not null,
  content_ref varchar(255) not null,
  description varchar(255) not null,
  price decimal(19,2)
)

create table activity (
  id bigint not null,
  activity_date timestamp not null,
  activity_id binary not null,
  content_ref varchar(255) not null,
  description varchar(255) not null,
  price decimal(19,2),
  seats integer not null
)

create table subscription (
  id bigint not null,
  subscription_id binary not null,
  activity_id bigint not null,
  email varchar(255) not null,
  first_name varchar(255) not null,
  last_name varchar(255) not null
)

create table activity_subscription_list (
  db_activity_id bigint not null,
  subscription_list_id bigint not null
)
