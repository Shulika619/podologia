create table Category
(
    id          int primary key generated ALWAYS AS IDENTITY,
    name        varchar(100) unique not null,
    description varchar(550),
    enabled     bool                not null default true
);
-- -----------------------------
create table Procedure
(
    id          int primary key generated ALWAYS AS IDENTITY,
    category_id int references Category (id),
    name        varchar(255) not null unique,
    enabled     bool         not null default true
);
-- -----------------------------
create table Specialist
(
    id          int primary key generated ALWAYS AS IDENTITY,
    name        varchar(255) not null unique,
    description varchar(550),
    enabled     bool         not null default true
);
-- -----------------------------
create table Price
(
    id            int primary key generated ALWAYS AS IDENTITY,
    procedure_id  int references Procedure (id),
    specialist_id int references Specialist (id),
    minutes       int not null,
    price         int not null
);
-- -----------------------------