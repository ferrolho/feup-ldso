# --- !Ups

create table "supply" (
  "id" VARCHAR NOT NULL PRIMARY KEY,
  "userID"  VARCHAR NOT NULL REFERENCES "user",
  "resource" VARCHAR,
  "amount" INTEGER
);


# --- !Downs

drop table "supply";
