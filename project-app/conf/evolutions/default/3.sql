# --- !Ups

create table "sortingCenterStock" (
  "id" VARCHAR  NOT NULL PRIMARY KEY,
  "idSupply" VARCHAR NOT NULL REFERENCES "supply",
  "idSortingCenter" VARCHAR NOT NULL,
  "userID"  VARCHAR NOT NULL REFERENCES "user",
  "resource" VARCHAR,
  "amount" INTEGER ,
  "inSortingCenter" BOOLEAN NOT NULL
);


# --- !Downs

drop table "sortingCenterStock";
