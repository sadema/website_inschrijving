create sequence hibernate_sequence;

create table product (
  id bigint not null,
  description varchar(255),
  price decimal
)
