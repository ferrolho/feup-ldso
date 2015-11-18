# --- !Ups

create table "sortingCenterStock" (
  "idResource" VARCHAR NOT NULL PRIMARY KEY,
  "idSortingCenter" VARCHAR NOT NULL,
  "userID"  VARCHAR NOT NULL REFERENCES "user",
  "resource" VARCHAR,
  "amount" INTEGER ,
  "inSortingCenter" BOOLEAN NOT NULL
);


# --- !Downs

drop table "sortingCenterStock";
