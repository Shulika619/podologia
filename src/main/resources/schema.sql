create table Category
(
    id          int primary key generated ALWAYS AS IDENTITY,
    name        varchar(100) unique not null,
    description varchar(550),
    enabled     bool                not null default true
);

INSERT INTO Category (name, description, enabled)
VALUES ('Профильные подологические услуги',
        'ПОДОЛОГ-ЭКСПЕРТ - Ефимова Иванна Александровна. ПОДОЛОГ - Шаповалова Татьяна, Мамалига Ирина', true);

INSERT INTO Category (name, description, enabled)
VALUES ('test', 'test category description', false);

-- -----------------------------

create table Procedure
(
    id          int primary key generated ALWAYS AS IDENTITY,
    category_id int references Category (id),
    name        varchar(255) not null unique,
    enabled     bool         not null default true
);
INSERT INTO Procedure(category_id, name, enabled)
VALUES (1, 'Консультация (заведение карты, расписание терапии, сбор анализа, фото до)', true);

INSERT INTO Procedure(category_id, name, enabled)
VALUES (1, 'Педикюр сложный (натоптыши, трещины, мозоли до 5шт, онихофогрифоз 7-10 ногдей врастающие ногти 1-2 стадии(боль, легкое покраснение))', true);

INSERT INTO Procedure(category_id, name, enabled)
VALUES (1, 'Педикюр пальцев сложный (внутренние мозоли, пустоты, утолщения, микоз)', true);

INSERT INTO Procedure(category_id, name, enabled)
VALUES (1, 'Test procedure', false);
--
create table Specialist
(
    id          int primary key generated ALWAYS AS IDENTITY,
    name        varchar(255) not null unique,
    description varchar(550),
    enabled     bool         not null default true
);
INSERT INTO Specialist(name, description, enabled)
VALUES ('Эксперт подолог', '', true);

INSERT INTO Specialist(name, description, enabled)
VALUES ('Подолог', '', true);

INSERT INTO Specialist(name, description, enabled)
VALUES ('Практик подолог', '', false);
--
create table Price
(
    id            int primary key generated ALWAYS AS IDENTITY,
    procedure_id  int references Procedure (id),
    specialist_id int references Specialist (id),
    minutes       int not null,
    price         int not null
);
INSERT INTO Price(procedure_id, specialist_id, minutes, price)
VALUES (1, 1, 30, 700);

INSERT INTO Price(procedure_id, specialist_id, minutes, price)
VALUES (1, 2, 30, 500);

INSERT INTO Price(procedure_id, specialist_id, minutes, price)
VALUES (2, 1, 90, 3000);

INSERT INTO Price(procedure_id, specialist_id, minutes, price)
VALUES (2, 2, 120, 2500);

-- -----------------------------