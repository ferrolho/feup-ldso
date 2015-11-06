# --- !Ups

create table "photos" (
  "id" bigint generated by default as identity(start with 1) not null primary key,
  "name" varchar not null,
  "url" varchar not null
);

# --- !Downs

drop table "photos" if exists;
