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