# --- !Ups

create table "sortingCenterStock" (
  -- id of this stock
  "id" VARCHAR  NOT NULL PRIMARY KEY,

  -- id of the supply
  "idSupply" VARCHAR NOT NULL,

  -- id of the SC user that is storing this stock
  "userID" VARCHAR NOT NULL REFERENCES "user",

  -- id of the user who supplied this resource
  "supplyUserID" VARCHAR NOT NULL REFERENCES "user",

  -- resource name/description
  "resource" VARCHAR,

  -- resource category id
  "resourceCategoryID" LONG REFERENCES "resourceCategory",

  -- resource amount
  "amount" INTEGER,

  -- resource amount label id
  "amountLabelID" LONG REFERENCES "resourceAmountLabel"
);


# --- !Downs

drop table "sortingCenterStock";
