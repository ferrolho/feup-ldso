# --- !Ups

create table "transport" (
  "id" VARCHAR  NOT NULL PRIMARY KEY,
  "idSourceUser" VARCHAR NOT NULL REFERENCES "user",
  "idDestinyUser" VARCHAR NOT NULL REFERENCES "user",
  "idSCStock" VARCHAR NOT NULL REFERENCES "sortingCenterStock",
  "active" BOOLEAN,
  "idTransporter" VARCHAR REFERENCES "user"
);

# --- !Downs

drop table "transport";
