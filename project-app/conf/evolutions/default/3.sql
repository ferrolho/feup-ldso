# --- !Ups

create table "sortingCenterStock" (
  "id" VARCHAR  NOT NULL PRIMARY KEY,
  "idSupply" VARCHAR NOT NULL REFERENCES "supply",
  "userID"  VARCHAR NOT NULL REFERENCES "user",
  "resource" VARCHAR,
  "amount" INTEGER
);


# --- !Downs

drop table "sortingCenterStock";
