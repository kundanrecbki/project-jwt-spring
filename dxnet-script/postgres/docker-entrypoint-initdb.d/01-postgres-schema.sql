CREATE USER postgres_user with encrypted password 'postgres_password' REPLICATION LOGIN;

CREATE SCHEMA user_schema;

GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres_user;

GRANT ALL PRIVILEGES ON SCHEMA user_schema TO postgres_user;

CREATE PUBLICATION dbz_publication FOR ALL TABLES;