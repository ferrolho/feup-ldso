# --- !Ups

create table "user_roles" (
  "userID" VARCHAR NOT NULL REFERENCES "user",
  "supplier" boolean NOT NULL,
  "sortingCenter" boolean NOT NULL,
  "consumer" boolean NOT NULL,
  "transporter" boolean NOT NULL
);


# --- !Downs

drop table "user_roles";
