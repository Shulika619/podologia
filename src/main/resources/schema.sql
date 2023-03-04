create table Category(
                     id int primary key generated ALWAYS AS IDENTITY,
                     category_name varchar(100) unique not null ,
                     description varchar(550),
                     status varchar(20) default 'ACTIVE'
);

INSERT INTO Category (category_name, description)
VALUES ('Профильные подологические услуги', 'ПОДОЛОГ-ЭКСПЕРТ - Ефимова Иванна Александровна. ПОДОЛОГ - Шаповалова Татьяна, Мамалига Ирина');

INSERT INTO Category (category_name, description)
VALUES ('test','test category 22222222222');