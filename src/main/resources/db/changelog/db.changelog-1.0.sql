--liquibase formatted sql
--changeset shulika:1
CREATE TABLE IF NOT EXISTS Category
(
    id          int primary key generated ALWAYS AS IDENTITY,
    name        varchar(100) unique not null,
    description varchar(550),
    enabled     bool                not null default true,
    created_at  timestamp           not null default CURRENT_TIMESTAMP,
    updated_at  timestamp           not null default CURRENT_TIMESTAMP
);
--rollback DROP TABLE category;

--changeset shulika:2
CREATE TABLE IF NOT EXISTS Procedure
(
    id          int primary key generated ALWAYS AS IDENTITY,
    category_id int references Category (id),
    name        varchar(256) not null unique,
    enabled     bool         not null default true,
    created_at  timestamp    not null default CURRENT_TIMESTAMP,
    updated_at  timestamp    not null default CURRENT_TIMESTAMP
);
--rollback DROP TABLE procedure;

--changeset shulika:3
CREATE TABLE IF NOT EXISTS Specialist
(
    id          int primary key generated ALWAYS AS IDENTITY,
    name        varchar(256) not null unique,
    description varchar(550),
    enabled     bool         not null default true,
    created_at  timestamp    not null default CURRENT_TIMESTAMP,
    updated_at  timestamp    not null default CURRENT_TIMESTAMP
);
--rollback DROP TABLE specialist;

--changeset shulika:4
CREATE TABLE IF NOT EXISTS Price
(
    id            int primary key generated ALWAYS AS IDENTITY,
    procedure_id  int references Procedure (id),
    specialist_id int references Specialist (id),
    minutes       int       not null,
    price         int       not null,
    created_at    timestamp not null default CURRENT_TIMESTAMP,
    updated_at    timestamp not null default CURRENT_TIMESTAMP
);
--rollback DROP TABLE price;