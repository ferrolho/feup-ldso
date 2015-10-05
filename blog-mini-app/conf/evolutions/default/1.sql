# Posts schema

# --- !Ups

CREATE TABLE "posts" (
    "id" bigint generate by default as IDENTITY (start with 1) NOT NULL PRIMARY KEY,
    "subject" varchar NOT NULL,
    "description" varchar NOT NULL
);

# --- !Downs

DROP TABLE "Post" if EXISTS;
