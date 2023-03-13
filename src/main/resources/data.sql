INSERT INTO Category (name, description, enabled)
VALUES ('Профильные подологические услуги','ПОДОЛОГ-ЭКСПЕРТ - Ефимова Иванна Александровна. ПОДОЛОГ - Шаповалова Татьяна, Мамалига Ирина', true)
ON CONFLICT (name) DO NOTHING;

INSERT INTO Category (name, description, enabled)
VALUES ('test', 'test category description', false)
ON CONFLICT (name) DO NOTHING;
-----------------------------
INSERT INTO Procedure(category_id, name, enabled)
VALUES (1, 'Консультация (заведение карты, расписание терапии, сбор анализа, фото до)', true)
ON CONFLICT (name) DO NOTHING;

INSERT INTO Procedure(category_id, name, enabled)
VALUES (1, 'Педикюр сложный (натоптыши, трещины, мозоли до 5шт, онихофогрифоз 7-10 ногдей врастающие ногти 1-2 стадии(боль, легкое покраснение))', true)
ON CONFLICT (name) DO NOTHING;

INSERT INTO Procedure(category_id, name, enabled)
VALUES (1, 'Педикюр пальцев сложный (внутренние мозоли, пустоты, утолщения, микоз)', true)
ON CONFLICT (name) DO NOTHING;
-----------------------------
INSERT INTO Specialist(name, description, enabled)
VALUES ('Эксперт подолог', '', true)
ON CONFLICT (name) DO NOTHING;

INSERT INTO Specialist(name, description, enabled)
VALUES ('Подолог', '', true)
ON CONFLICT (name) DO NOTHING;

INSERT INTO Specialist(name, description, enabled)
VALUES ('Практик подолог', '', false)
ON CONFLICT (name) DO NOTHING;
-----------------------------
----------------------------- TODO: init always -> add new
INSERT INTO Price(procedure_id, specialist_id, minutes, price)
VALUES (1, 1, 30, 700);
--
-- INSERT INTO Price(procedure_id, specialist_id, minutes, price)
-- VALUES (1, 2, 30, 500);
--
-- INSERT INTO Price(procedure_id, specialist_id, minutes, price)
-- VALUES (2, 1, 90, 3000);
--
-- INSERT INTO Price(procedure_id, specialist_id, minutes, price)
-- VALUES (2, 2, 120, 2500);


