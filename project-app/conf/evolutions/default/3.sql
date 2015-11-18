# --- !Ups

create table "sortingCenterWarehouse" (
  "idResource" VARCHAR NOT NULL PRIMARY KEY,
  "idSortingCenter" VARCHAR NOT NULL,
  "userID"  VARCHAR NOT NULL REFERENCES "user",
  "resource" VARCHAR,
  "amount" INTEGER ,
  "inSortingCenter" BOOLEAN NOT NULL
);


# --- !Downs

drop table "sortingCenterWarehouse";
