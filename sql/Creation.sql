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
    idAction       INT NOT NULL,
    idPortefeuille INT NOT NULL,
    quantity       INT NOT NULL,
    PRIMARY KEY (idAction, idPortefeuille),
    FOREIGN KEY (idPortefeuille) REFERENCES Portefeuille (id),
    FOREIGN KEY (idAction) REFERENCES Action (id)
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
    idAction       INT                                        NOT NULL,
    price          FLOAT                                     NOT NULL,
    date           timestamp                                 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (idPortefeuille) REFERENCES Portefeuille (id),
    FOREIGN KEY (idAction) REFERENCES Action (id)
);



