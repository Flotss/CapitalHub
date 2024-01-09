drop table if exists Event;
drop table if exists History;
drop table if exists Transaction;
drop table if exists TransactionType;
drop table if exists Currency;
drop table if exists PortefeuilleProduit;
drop table if exists Actions;
drop table if exists Portefeuille;

create table if not exists Portefeuille
(
    idPortefeuille          int          not null,
    idUtilisateur           int not null,
    nomPortefeuille         varchar(255) not null,
    descriptionPortefeuille varchar(255),
    primary key (idPortefeuille)
);

create table if not exists Actions
(
    idmarche int   not null,
    nom      varchar(225) not null,
    valeur   float not null,
    primary key (idmarche)
);

create table if not exists PortefeuilleProduit
(
    idProduit int not null,
    quantité       int          not null,
    idPortefeuille int          not null,
    primary key (idProduit, idPortefeuille),
    foreign key (idPortefeuille) references Portefeuille (idPortefeuille),
    foreign key (idProduit) references Actions (idMarche)
);


create table if not exists Currency
(
    code    varchar(255) not null,
    name    varchar(255) not null,
    symbole varchar(255) not null,
    primary key (code)
);

create table if not exists TransactionType
(
    transactionType varchar(255) not null,
    primary key (transactionType)
);


create table if not exists Transaction
(
    idTransaction   int          not null,
    idPortefeuille  int          not null,
    prix            float        not null,
    dateTransaction date         not null,
    codeCurrency varchar(255) not null,
    transactionType varchar(255) not null,
    valeur       float        not null,
    primary key (idTransaction),
    foreign key (idPortefeuille) references Portefeuille (idPortefeuille),
    foreign key (codeCurrency) references Currency (code),
    foreign key (transactionType) references TransactionType (transactionType)
);



create table if not exists History
(
    idHistory      int not null,
    idPortefeuille int not null,
    primary key (idHistory),
    foreign key (idPortefeuille) references Portefeuille (idPortefeuille)
);

create table if not exists Event
(
    idEvent       int          not null,
    idHistory     int          not null,
    idTransaction int          not null,
    dateEvent     date         not null,
    description varchar(255),
    primary key (idEvent),
    foreign key (idHistory) references History (idHistory),
    foreign key (idTransaction) references Transaction (idTransaction)
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
(idportefeuille, idutilisateur, nomportefeuille, descriptionportefeuille)
VALUES (1, 1, 'TestPorteFeuille', 'Mon Test de portefeuille');
INSERT INTO Portefeuille (idportefeuille, idutilisateur, nomportefeuille, descriptionportefeuille)
VALUES (2, 1, 'TestPorteFeuille2', 'Mon Test de portefeuille2');

insert into Actions (idmarche, nom, valeur)
values (1, 'Bitcoin', 10000);
insert into Actions (idmarche, nom, valeur)
values (2, 'Ethereum', 1000);
insert into Actions (idmarche, nom, valeur)
values (3, 'Tesla', 233.96);
insert into Actions (idmarche, nom, valeur)
values (4, 'Apple', 133.11);
insert into Actions (idmarche, nom, valeur)
values (5, 'Amazon', 3206.22);
insert into Actions (idmarche, nom, valeur)
values (6, 'Facebook', 257.62);
insert into Actions (idmarche, nom, valeur)
values (7, 'Netflix', 503.18);



insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (1, 1, 10, timestamp '2024-01-01 9:00:00', 'EUR', 'Buy', 10);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (2, 1, 10, timestamp '2024-01-01 10:00:00', 'EUR', 'Buy', 10);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (3, 1, 10, timestamp '2024-01-01 11:00:00', 'EUR', 'Sell', 10);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (4, 1, 10, timestamp '2024-01-01 12:00:00', 'EUR', 'Buy', 10);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (5, 1, 10, timestamp '2024-01-01 13:00:00', 'EUR', 'Sell', 10);

insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (6, 2, 10, timestamp '2024-01-01 14:00:00', 'EUR', 'Sell', 10);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (7, 2, 10, timestamp '2024-01-01 15:00:00', 'EUR', 'Buy', 100);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (8, 2, 10, timestamp '2024-01-01 16:00:00', 'EUR', 'Sell', 10);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (9, 2, 10, timestamp '2024-01-01 17:00:00', 'EUR', 'Buy', 200);
insert into Transaction (idTransaction, idPortefeuille, prix, dateTransaction, codeCurrency, transactionType, valeur)
values (10, 2, 10, timestamp '2024-01-01 18:00:00', 'EUR', 'Sell', 100);


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





