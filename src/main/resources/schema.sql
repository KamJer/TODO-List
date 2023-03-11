CREATE TABLE "USER" (
    id INTEGER NOT NULL AUTO_INCREMENT,
    login VARCHAR(128),
    password VARCHAR(128),
    name VARCHAR(128),
    PRIMARY KEY (id)
    );

CREATE TABLE TODOLIST (
    id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    name VARCHAR(128),
    updated_timestamp TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES "USER"(id)
     );

CREATE TABLE TODOITEM (
     id INTEGER NOT NULL AUTO_INCREMENT,
     list_id INTEGER NOT NULL,
     "value" VARCHAR(128),
     stage INTEGER NOT NULL,
     updated_timestamp TIMESTAMP,
     PRIMARY KEY (id),
     FOREIGN KEY (list_id) REFERENCES TODOLIST(id)
     );