insert into Currency (code, name, symbole)
values ('EUR', 'Euro', '€');
insert into Currency (code, name, symbole)
values ('USD', 'Dollar', '$');


insert into TransactionType (transactionType)
values ('Buy');
insert into TransactionType (transactionType)
values ('Sell');

INSERT INTO usertable (username, password)
VALUES ('test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');
INSERT INTO usertable (username, password)
VALUES ('test2', '60303ae22b998861bce3b28f33eec1be758a213c86c93c076dbe9f558c11c752');


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


-- Portefeuille 1 achète 10 Bitcoins
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 1, 10000 * 10, '2024-01-01 10:00:00', 'EUR', 'Buy');

INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 2, 1000 * 15, '2024-01-02 10:00:00', 'EUR', 'Buy');

INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (1, 1, 10500 * 5, '2024-01-03 10:00:00', 'EUR', 'Sell');


-- Portefeuille 2 achète 20 Tesla
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (2, 4, 133.11 * 20, '2024-01-01 10:00:00', 'USD', 'Buy');
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (2, 3, 233.96 * 30, '2024-01-02 10:00:00', 'USD', 'Buy');
INSERT INTO Transaction (idPortefeuille, idAction, price, date, codeCurrency, type)
VALUES (2, 3, 240.00 * 10, '2024-01-03 10:00:00', 'USD', 'Sell');


-- Mise à jour pour le Bitcoin dans le Portefeuille 1 (10 achetés, 5 vendus)
INSERT INTO ActionProduit (idAction, idPortefeuille, quantity)
VALUES (1, 1, 10 - 5); -- 5 Bitcoins restants

-- Mise à jour pour l'Ethereum dans le Portefeuille 1 (15 achetés)
INSERT INTO ActionProduit (idAction, idPortefeuille, quantity)
VALUES (2, 1, 15);


-- Mise à jour pour les actions Apple dans le Portefeuille 2 (20 achetées)
INSERT INTO ActionProduit (idAction, idPortefeuille, quantity)
VALUES (4, 2, 20);

-- Mise à jour pour les actions Tesla dans le Portefeuille 2 (30 achetées, 10 vendues)
INSERT INTO ActionProduit (idAction, idPortefeuille, quantity)
VALUES (3, 2, 30 - 10); -- 20 actions Tesla restantes



-- Variation du prix de Bitcoin pour le Portefeuille 1
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 10200, '2024-01-02 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 9800, '2024-01-03 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 10700, '2024-01-04 10:00:00');

-- Variation du prix de Ethereum pour le Portefeuille 1
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 950, '2024-01-02 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 1100, '2024-01-03 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 1000, '2024-01-04 9:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 1000, '2024-01-12 10:30:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 1200, '2024-01-12 11:50:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 3, 5000, '2024-01-12 13:20:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 3, 4000, '2024-01-12 14:10:00');



-- Variation du prix d'Apple pour le Portefeuille 2
-- delete from History where idPortefeuille = 2 and idAction = 4;
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 135.50, '2024-01-02 10:01:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 130.00, '2024-01-03 10:30:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 140.00, '2024-01-04 10:40:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 200, '2024-01-04 11:40:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 250, '2024-01-04 19:40:00');



-- Variation du prix de Tesla pour le Portefeuille 2
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 235.00, '2024-01-02 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 230.00, '2024-01-03 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 250.00, '2024-01-04 10:00:00');




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



