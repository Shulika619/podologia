--liquibase formatted sql

--changeset shulika:1
CREATE TABLE IF NOT EXISTS _user
(
    id         int primary key generated ALWAYS AS IDENTITY,
    email      varchar(256) not null unique,
    firstname  varchar(256) not null,
    lastname   varchar(256) not null,
    password   varchar(256) not null,
    role       varchar(10)           default 'USER',
    enabled    bool                  default true,
    created_at timestamp    not null default CURRENT_TIMESTAMP,
    updated_at timestamp    not null default CURRENT_TIMESTAMP
);
--rollback DROP TABLE _user;

--changeset shulika:2
INSERT INTO _user (email, firstname, lastname, password, role)
VALUES ('admin@gmail.com', 'Admin', 'Adminovich', '$2a$10$GRDBlFaea7.ZSp82J2UM1.Y5QTnY4FOyftvGPmY65CNhH2QFH7CNa', 'ADMIN')
ON CONFLICT (email) DO NOTHING;

INSERT INTO _user (email, firstname, lastname, password, role)
VALUES ('manager@gmail.com', 'Manager', 'Managerovich', '$2a$10$GRDBlFaea7.ZSp82J2UM1.Y5QTnY4FOyftvGPmY65CNhH2QFH7CNa', 'MANAGER')
ON CONFLICT (email) DO NOTHING;

INSERT INTO _user (email, firstname, lastname, password,role)
VALUES ('user@gmail.com', 'User', 'Userovich', '$2a$10$GRDBlFaea7.ZSp82J2UM1.Y5QTnY4FOyftvGPmY65CNhH2QFH7CNa', 'USER')
ON CONFLICT (email) DO NOTHING;

--changeset shulika:3
CREATE TABLE IF NOT EXISTS token
(
    id         int primary key generated ALWAYS AS IDENTITY,
    token      varchar(256) not null unique,
    token_type varchar(256) not null,
    revoked    bool,
    expired    bool,
    user_id    int references _user (id)
);
--rollback DROP TABLE token;