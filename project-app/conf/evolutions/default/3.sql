# --- !Ups

create table "sortingCenterStock" (
  "id" VARCHAR  NOT NULL PRIMARY KEY,
  "idSupply" VARCHAR NOT NULL,
  "userID" VARCHAR NOT NULL REFERENCES "user",
  "resource" VARCHAR,
  "resourceCategoryID" LONG REFERENCES "resourceCategory",
  "amount" INTEGER,
  "amountLabelID" LONG REFERENCES "resourceAmountLabel"
);


# --- !Downs

drop table "sortingCenterStock";
