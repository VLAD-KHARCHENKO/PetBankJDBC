drop schema if exists petBank;
create schema petBank character set utf8 collate utf8_general_ci;
use petBank;

create table user (
id bigint auto_increment,
first_name varchar(255),
last_name varchar(255),
email varchar(255) unique,
password varchar(255) not null,
isActive boolean,
role enum("CUSTOMER", "ADMIN"),
primary key (id));

create table account (
id bigint auto_increment,
number varchar(255),
balance decimal(15,2),
isActive boolean,
user_id bigint,
primary key (id),
foreign key (user_id) references user(id)
);

create table card(
id bigint auto_increment,
card_name enum("UNIVERSAL", "CREDIT", "INTERNET", "ANOTHER"),
isActive boolean,
account_id bigint,
primary key (id),
foreign key (account_id) references account(id)
);

create table card_list(
id bigint auto_increment,
account_id bigint,
card_id bigint,
primary key (id),
foreign key (account_id) references account(id),
foreign key (card_id) references card(id)
);

create table payment(
id bigint auto_increment,
`date` datetime,
debit_account_id bigint,
credit_account_id bigint,
amount decimal(15,2),
description varchar(255),
status enum("SAVE", "PAID"),
primary key (id),
foreign key (debit_account_id) references account(id),
foreign key (credit_account_id) references account(id)
);