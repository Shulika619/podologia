create table Category
(
    id            int primary key generated ALWAYS AS IDENTITY,
    category_name varchar(100) unique not null,
    description   varchar(550),
    is_active     bool not null default true
);

INSERT INTO Category (category_name, description, is_active)
VALUES ('Профильные подологические услуги',
        'ПОДОЛОГ-ЭКСПЕРТ - Ефимова Иванна Александровна. ПОДОЛОГ - Шаповалова Татьяна, Мамалига Ирина', true);

INSERT INTO Category (category_name, description, is_active)
VALUES ('test', 'test category description', false);

-- -------------------------

create table Procedure
(
    id                     int primary key generated ALWAYS AS IDENTITY,
    id_category            int references Category (id),
    procedure_name         varchar(255) not null unique,
    podolog_expert_price   int          not null,
    podolog_expert_minutes int          not null,
    podolog_price          int          not null,
    podolog_minutes        int          not null,
    is_active              bool         not null default true
);