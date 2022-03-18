CREATE TABLE "users" (
  "user_id" varchar PRIMARY KEY,
  "password" varchar NOT NULL,
  "user_name" varchar NOT NULL,
  "created_at" timestamp,
  "last_logout" timestamp
);

CREATE TABLE "tweets" (
  "tweet_id" SERIAL PRIMARY KEY,
  "user_id" varchar NOT NULL,
  "tweet_data" varchar NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp
);

ALTER TABLE "tweets" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");
