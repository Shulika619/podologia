--liquibase formatted sql

--changeset shulika:1
CREATE TABLE IF NOT EXISTS _user
(
    id         int primary key generated ALWAYS AS IDENTITY,
    email      varchar(256) not null unique,
    firstname  varchar(256) not null,
    lastname   varchar(256) not null,
    password   varchar(256) not null,
    enabled    bool         not null default true,
    created_at timestamp    not null default CURRENT_TIMESTAMP,
    updated_at timestamp    not null default CURRENT_TIMESTAMP
);
--rollback DROP TABLE _user;

--changeset shulika:2
INSERT INTO _user (email, firstname, lastname, password)
VALUES ('admin@gmail.com', 'Admin','Adminovich','1234')
ON CONFLICT (email) DO NOTHING;

INSERT INTO _user (email, firstname, lastname, password)
VALUES ('manager@gmail.com', 'Manager','Managerovich','1234')
ON CONFLICT (email) DO NOTHING;

INSERT INTO _user (email, firstname, lastname, password)
VALUES ('user@gmail.com', 'User','Userovich','1234')
ON CONFLICT (email) DO NOTHING;