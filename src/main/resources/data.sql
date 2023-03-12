INSERT INTO "USER" (login, password, name) VALUES ('Kamil', 'test', 'Kamil');
INSERT INTO "USER" (login, password, name) VALUES ('Jakub', 'test 2', 'Kuba');
INSERT INTO "USER" (login, password, name) VALUES ('Ezekiel', 'test 2', 'Ezekiel');
INSERT INTO TODOLIST (user_id, name) VALUES (3, 'Ezekiel list');
INSERT INTO TODOLIST (user_id, name) VALUES (3, 'Ezekiel list 2');
INSERT INTO TODOLIST (user_id, name) VALUES (3, 'Lista zadań na dziś');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (1, 'Zakupy', 'DONE');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (1, 'Zakupy 2', 'DONE');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (1, 'Zakupy 3', 'DONE');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (1, 'Zakupy 4', 'DONE');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (1, 'Zakupy 5', 'DONE');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (1, 'Zakupy 5', 'DONE');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (2, 'iść na spacer', 'DONE');
INSERT INTO TODOITEM (list_id, "VALUE", stage) VALUES (2, 'Zakupy', 'DONE');

