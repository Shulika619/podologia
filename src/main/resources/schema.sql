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
    category_id            int references Category (id),
    name         varchar(255) not null unique,
    podolog_expert_minutes int          not null,
    podolog_expert_price   int          not null,
    podolog_minutes        int          not null,
    podolog_price          int          not null,
    enabled              bool         not null default true
);

INSERT INTO Procedure(category_id, name, podolog_expert_minutes, podolog_expert_price, podolog_minutes, podolog_price, enabled)
VALUES (1, 'Консультация (заведение карты, расписание терапии, сбор анализа, фото до)', 30, 700, 30, 500, true);

INSERT INTO Procedure(category_id, name, podolog_expert_minutes, podolog_expert_price, podolog_minutes, podolog_price, enabled)
VALUES (1, 'Педикюр сложный (натоптыши, трещины, мозоли до 5шт, онихофогрифоз 7-10 ногдей врастающие ногти 1-2 стадии(боль, легкое покраснение))', 90, 2300, 120, 2000, true);

INSERT INTO Procedure(category_id, name, podolog_expert_minutes, podolog_expert_price, podolog_minutes, podolog_price, enabled)
VALUES (1, 'Педикюр пальцев сложный (внутренние мозоли, пустоты, утолщения, микоз)', 60, 1150, 60, 1000, true);

INSERT INTO Procedure(category_id, name, podolog_expert_minutes, podolog_expert_price, podolog_minutes, podolog_price, enabled)
VALUES (1, 'Test procedure', 0, 0, 0, 0, false);