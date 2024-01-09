drop table if exists Event;
drop table if exists History;
drop table if exists Transaction;
drop table if exists TransactionType;
drop table if exists Currency;
drop table if exists ActionProduit;
drop table if exists Action;
drop table if exists Portefeuille;

CREATE SEQUENCE if not exists portefeuille_id_sequence START 1;
CREATE SEQUENCE if not exists actions_id_sequence START 1;
CREATE SEQUENCE if not exists actionproduit_id_sequence START 1;
CREATE SEQUENCE if not exists transaction_id_sequence START 1;
CREATE SEQUENCE if not exists history_id_sequence START 1;
CREATE SEQUENCE if not exists event_id_sequence START 1;

ALTER SEQUENCE portefeuille_id_sequence RESTART WITH 1;
ALTER SEQUENCE actions_id_sequence RESTART WITH 1;
ALTER SEQUENCE actionproduit_id_sequence RESTART WITH 1;
ALTER SEQUENCE transaction_id_sequence RESTART WITH 1;
ALTER SEQUENCE history_id_sequence RESTART WITH 1;
ALTER SEQUENCE event_id_sequence RESTART WITH 1;

CREATE TABLE IF NOT EXISTS Portefeuille
(
    id          INT DEFAULT nextval('portefeuille_id_sequence') NOT NULL,
    idUser      INT                                             NOT NULL,
    name        VARCHAR(255)                                    NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Action
(
    id    INT DEFAULT nextval('actions_id_sequence') NOT NULL,
    name  VARCHAR(225)                               NOT NULL,
    value FLOAT                                      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ActionProduit
(
    id             INT DEFAULT nextval('actionproduit_id_sequence') NOT NULL,
    idPortefeuille INT                                              NOT NULL,
    quantity       INT                                              NOT NULL,
    PRIMARY KEY (id, idPortefeuille),
    FOREIGN KEY (idPortefeuille) REFERENCES Portefeuille (id),
    FOREIGN KEY (id) REFERENCES Action (id)
);

CREATE TABLE IF NOT EXISTS Currency
(
    code    VARCHAR(255) NOT NULL,
    name    VARCHAR(255) NOT NULL,
    symbole VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
);

CREATE TABLE IF NOT EXISTS TransactionType
(
    transactionType VARCHAR(255) NOT NULL,
    PRIMARY KEY (transactionType)
);

CREATE TABLE IF NOT EXISTS Transaction
(
    id             INT DEFAULT nextval('transaction_id_sequence') NOT NULL,
    idPortefeuille INT                                            NOT NULL,
    idAction       INT                                            NOT NULL,
    price          FLOAT                                          NOT NULL,
    date           timestamp                                      NOT NULL,
    codeCurrency   VARCHAR(255)                                   NOT NULL,
    type           VARCHAR(255)                                   NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (idPortefeuille) REFERENCES Portefeuille (id),
    FOREIGN KEY (idAction) REFERENCES Action (id),
    FOREIGN KEY (codeCurrency) REFERENCES Currency (code),
    FOREIGN KEY (type) REFERENCES TransactionType (transactionType)
);

CREATE TABLE IF NOT EXISTS History
(
    id             INT DEFAULT nextval('history_id_sequence') NOT NULL,
    idPortefeuille INT                                        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (idPortefeuille) REFERENCES Portefeuille (id)
);

CREATE TABLE IF NOT EXISTS Event
(
    id            INT DEFAULT nextval('event_id_sequence') NOT NULL,
    idHistory     INT                                      NOT NULL,
    idTransaction INT                                      NOT NULL,
    dateEvent     timestamp                                NOT NULL,
    description   VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (idHistory) REFERENCES History (id),
    FOREIGN KEY (idTransaction) REFERENCES Transaction (id)
);

insert into Currency (code, name, symbole)
values ('EUR', 'Euro', '€');
insert into Currency (code, name, symbole)
values ('USD', 'Dollar', '$');


insert into TransactionType (transactionType)
values ('Buy');
insert into TransactionType (transactionType)
values ('Sell');

INSERT INTO Portefeuille
    (iduser, name, description)
VALUES (1, 'TestPorteFeuille', 'Mon Test de portefeuille');
INSERT INTO Portefeuille (iduser, name, description)
VALUES (1, 'TestPorteFeuille2', 'Mon Test de portefeuille2');

insert into Action (name, value)
values ('Bitcoin', 10000);
insert into Action (name, value)
values ('Ethereum', 1000);
insert into Action (name, value)
values ('Tesla', 233.96);
insert into Action (name, value)
values ('Apple', 133.11);
insert into Action (name, value)
values ('Amazon', 3206.22);
insert into Action (name, value)
values ('Facebook', 257.62);
insert into Action (name, value)
values ('Netflix', 503.18);


-- Transactions pour Bitcoin (Action ID 1)
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 1, 10000, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy'),
       (2, 1, 1000, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy'),
       (1, 1, 3000, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell'),
       (1, 1, 50, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy'),
       (2, 1, 500, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell');

-- Transactions pour Ethereum (Action ID 2)
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 2, 1000, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy'),
       (1, 2, 1100, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy'),
       (1, 2, 500, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell'),
       (1, 2, 100, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy'),
       (1, 2, 480, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell');

-- Transactions pour Tesla (Action ID 3)
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 3, 500, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy'),
       (1, 3, 100, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy'),
       (1, 3, 100, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell'),
       (1, 3, 127, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy'),
       (1, 3, 50, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell');

-- Transactions pour Apple (Action ID 4)
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 4, 300, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy'),
       (1, 4, 150, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy'),
       (1, 4, 300, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell'),
       (1, 4, 200, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy'),
       (1, 4, 50, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell');

-- Transactions pour Amazon (Action ID 5)
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 5, 3000, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy'),
       (1, 5, 2000, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy'),
       (1, 5, 4000, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell'),
       (1, 5, 3000, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy'),
       (1, 5, 1000, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell');

-- Transactions pour Facebook (Action ID 6)
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 6, 200, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy'),
       (1, 6, 250, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy'),
       (1, 6, 50, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell'),
       (1, 6, 40, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy'),
       (1, 6, 200, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell');

-- Transactions pour Netflix (Action ID 7)
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 7, 503.18, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy'),
       (1, 7, 505.00, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy'),
       (1, 7, 499.75, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell'),
       (1, 7, 510.50, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy'),
       (1, 7, 300, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell');


-- -- select all action from portefeuille -> transaction -> action
-- select Distinct (a.*)
-- from Portefeuille p
--          join Transaction t on p.id = t.idPortefeuille
--          join Action a on t.idAction = a.id
-- where p.id = 1;



-- delete from Transaction where idTransaction = 1;
--
--
-- select *
-- from Portefeuille;
--
--
--
-- -- delete all
-- delete
-- from Portefeuille
-- where idUtilisateur = 1;
--





