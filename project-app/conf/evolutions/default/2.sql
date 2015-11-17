# --- !Ups

--- Supplies
create table "supply" (
  "id" VARCHAR NOT NULL PRIMARY KEY,
  "userID"  VARCHAR NOT NULL REFERENCES "user",
  "resource" VARCHAR,
  "amount" INTEGER
);

--- Resource categories
create table "resourceCategory" (
  "id" VARCHAR NOT NULL PRIMARY KEY,
  "name" VARCHAR
);

insert into "resourceCategory"
("name")
values
('Breads'),
('Vegetables'),
('Meat'),
('Sea Food'),
('Dairy'),
('Grains (pasta, rice, etc)'),
('Fruits'),
('Condiments (sauces, pesto, etc)'),
('Drinkables'),
('Miscellaneous (Chips, biscuits, cooking oils, etc)');

--- Resource amount label
create table "resourceAmountLabel" (
  "id" VARCHAR NOT NULL PRIMARY KEY,
  "name" VARCHAR
);

insert into "resourceAmountLabel"
("name")
values
('Quantity'),
('Kg'),
('Pieces'),
('Packets'),
('Cans'),
('Boxes'),
('Bottles'),
('Crates');


# --- !Downs

drop table "supply";
drop table "resourceCategory";
drop table "resourceAmountLabel";
