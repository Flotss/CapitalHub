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

insert into Action (name, value, symbol)
values ('Bitcoin', 38917, '');
insert into Action (name, value, symbol)
values ('Ethereum', 2311.94, '');
insert into Action(name, value, symbol)
values ('Solana', 95.57, '');
insert into Action (name, value, symbol)
values ('Tesla', 218.89, 'TSLA');
insert into Action (name, value, symbol)
values ('Apple', 195.92, 'AAPL');
insert into Action (name, value, symbol)
values ('Amazon', 154.2, 'AMZN');
insert into Action (name, value, symbol)
values ('Meta', 374.62, 'META');
insert into Action (name, value, symbol)
values ('Netflix', 492.18, 'NFLX');
insert into Action (name, value, symbol)
values ('The Walt Disney Company', 90, 'DIS');


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
VALUES (2, 1, 15 - 5);


-- Mise à jour pour les actions Apple dans le Portefeuille 2 (20 achetées)
INSERT INTO ActionProduit (idAction, idPortefeuille, quantity)
VALUES (4, 2, 20 - 3);

-- Mise à jour pour les actions Tesla dans le Portefeuille 2 (30 achetées, 10 vendues)
INSERT INTO ActionProduit (idAction, idPortefeuille, quantity)
VALUES (3, 2, 30 - 10); -- 20 actions Tesla restantes


INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 39436.76 * 5, '2024-01-01 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2323.48 * 10, '2024-01-01 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 95.47 * 17, '2024-01-01 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 219.30, '2024-01-01 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40119.46 * 5, '2024-01-02 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2277.79 * 10, '2024-01-02 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 93.91 * 17, '2024-01-02 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 217.02 * 20, '2024-01-02 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 39903.03 * 5, '2024-01-03 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2304.03 * 10, '2024-01-03 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 92.07 * 17, '2024-01-03 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 216.74 * 20, '2024-01-03 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40143.99 * 5, '2024-01-04 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2261.07 * 10, '2024-01-04 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 90.99 * 17, '2024-01-04 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 213.96 * 20, '2024-01-04 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40762.31 * 5, '2024-01-05 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2271.28 * 10, '2024-01-05 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 90.88 * 17, '2024-01-05 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 214.77 * 20, '2024-01-05 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40445.79 * 5, '2024-01-06 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2306.00 * 10, '2024-01-06 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 91.82 * 17, '2024-01-06 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 214.14 * 20, '2024-01-06 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40770.46 * 5, '2024-01-07 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2333.64 * 10, '2024-01-07 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 91.85 * 17, '2024-01-07 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 212.49 * 20, '2024-01-07 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40912.28 * 5, '2024-01-08 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2342.58 * 10, '2024-01-08 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 90.20 * 17, '2024-01-08 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 210.75 * 20, '2024-01-08 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 41063.24 * 5, '2024-01-09 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2355.38 * 10, '2024-01-09 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 89.60 * 17, '2024-01-09 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 207.58 * 20, '2024-01-09 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 41262.67 * 5, '2024-01-10 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2313.49 * 10, '2024-01-10 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 89.43 * 17, '2024-01-10 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 207.03 * 20, '2024-01-10 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 39139.88 * 5, '2024-01-11 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2338.74 * 10, '2024-01-11 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 97.14 * 17, '2024-01-11 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 220.86 * 20, '2024-01-11 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 39687.02 * 5, '2024-01-12 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2369.00 * 10, '2024-01-12 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 98.30 * 17, '2024-01-12 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 222.67 * 20, '2024-01-12 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 39427.19 * 5, '2024-01-13 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2360.19 * 10, '2024-01-13 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 100.08 * 17, '2024-01-13 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 221.40 * 20, '2024-01-13 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 39894.17 * 5, '2024-01-14 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2317.57 * 10, '2024-01-14 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 101.19 * 17, '2024-01-14 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 222.91 * 20, '2024-01-14 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 39861.58 * 5, '2024-01-15 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2297.70 * 10, '2024-01-15 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 101.07 * 17, '2024-01-15 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 224.05 * 20, '2024-01-15 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40422.54 * 5, '2024-01-16 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2290.67 * 10, '2024-01-16 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 100.18 * 17, '2024-01-16 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 224.77 * 20, '2024-01-16 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40414.21 * 5, '2024-01-17 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2250.78 * 10, '2024-01-17 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 101.61 * 17, '2024-01-17 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 220.57 * 20, '2024-01-17 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 40447.68 * 5, '2024-01-18 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 2235.26 * 10, '2024-01-18 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 102.39 * 17, '2024-01-18 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 217.08 * 20, '2024-01-18 00:00:00');


-- 19/01/2024
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 200148.50, '2024-01-19 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22460.78, '2024-01-19 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1762.66, '2024-01-19 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4285.65, '2024-01-19 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 199576.73, '2024-01-19 01:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22106.93, '2024-01-19 01:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1788.13, '2024-01-19 01:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4265.75, '2024-01-19 01:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 199417.93, '2024-01-19 02:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21734.62, '2024-01-19 02:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1784.20, '2024-01-19 02:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4266.81, '2024-01-19 02:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 203098.26, '2024-01-19 03:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22145.36, '2024-01-19 03:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1763.34, '2024-01-19 03:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4294.05, '2024-01-19 03:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 201003.27, '2024-01-19 04:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22128.48, '2024-01-19 04:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1739.36, '2024-01-19 04:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4217.98, '2024-01-19 04:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 199007.23, '2024-01-19 05:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22310.15, '2024-01-19 05:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1741.82, '2024-01-19 05:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4164.11, '2024-01-19 05:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 200865.09, '2024-01-19 06:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22296.73, '2024-01-19 06:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1767.93, '2024-01-19 06:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4177.74, '2024-01-19 06:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 198056.63, '2024-01-19 07:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22097.77, '2024-01-19 07:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1747.80, '2024-01-19 07:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4171.70, '2024-01-19 07:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 195976.30, '2024-01-19 08:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22063.06, '2024-01-19 08:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1759.93, '2024-01-19 08:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4235.43, '2024-01-19 08:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 195298.31, '2024-01-19 09:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21785.95, '2024-01-19 09:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1744.23, '2024-01-19 09:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4231.84, '2024-01-19 09:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 197084.68, '2024-01-19 10:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22149.60, '2024-01-19 10:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1766.19, '2024-01-19 10:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4313.47, '2024-01-19 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 197553.75, '2024-01-19 11:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21857.63, '2024-01-19 11:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1774.37, '2024-01-19 11:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4380.56, '2024-01-19 11:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 195688.20, '2024-01-19 12:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22243.01, '2024-01-19 12:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1758.62, '2024-01-19 12:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4336.61, '2024-01-19 12:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 195544.07, '2024-01-19 13:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22201.34, '2024-01-19 13:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1769.11, '2024-01-19 13:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4419.60, '2024-01-19 13:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 195037.31, '2024-01-19 14:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22391.76, '2024-01-19 14:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1741.90, '2024-01-19 14:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4374.76, '2024-01-19 14:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 196070.35, '2024-01-19 15:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22018.21, '2024-01-19 15:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1758.43, '2024-01-19 15:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4401.43, '2024-01-19 15:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 195057.65, '2024-01-19 16:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22114.82, '2024-01-19 16:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1786.73, '2024-01-19 16:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4456.92, '2024-01-19 16:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 191477.62, '2024-01-19 17:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21983.23, '2024-01-19 17:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1807.20, '2024-01-19 17:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4543.81, '2024-01-19 17:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 189482.17, '2024-01-19 18:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 22168.39, '2024-01-19 18:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1795.70, '2024-01-19 18:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4513.76, '2024-01-19 18:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 190970.30, '2024-01-19 19:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21802.77, '2024-01-19 19:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1762.46, '2024-01-19 19:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4590.12, '2024-01-19 19:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 188240.50, '2024-01-19 20:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21582.47, '2024-01-19 20:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1750.22, '2024-01-19 20:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4671.12, '2024-01-19 20:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 186365.16, '2024-01-19 21:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21954.88, '2024-01-19 21:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1733.98, '2024-01-19 21:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4609.36, '2024-01-19 21:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 183846.25, '2024-01-19 22:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21577.31, '2024-01-19 22:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1755.99, '2024-01-19 22:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4651.81, '2024-01-19 22:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 182658.25, '2024-01-19 23:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 21805.77, '2024-01-19 23:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1780.74, '2024-01-19 23:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4631.33, '2024-01-19 23:00:00');


delete
from history
where date > '2024-01-19 00:00:00';

-- TEST
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 185821.67, '2024-01-19 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 18916.86, '2024-01-19 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1471.49, '2024-01-19 00:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 3746.43, '2024-01-19 00:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 212644.29, '2024-01-19 01:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 20232.79, '2024-01-19 01:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1742.98, '2024-01-19 01:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4336.58, '2024-01-19 01:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 185201.07, '2024-01-19 02:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 23792.56, '2024-01-19 02:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1544.03, '2024-01-19 02:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4593.29, '2024-01-19 02:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 158556.41, '2024-01-19 03:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 24193.62, '2024-01-19 03:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1277.43, '2024-01-19 03:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 3893.06, '2024-01-19 03:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 179788.84, '2024-01-19 04:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 23721.82, '2024-01-19 04:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1290.09, '2024-01-19 04:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4543.17, '2024-01-19 04:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 153842.02, '2024-01-19 05:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 25637.98, '2024-01-19 05:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1381.31, '2024-01-19 05:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 4658.49, '2024-01-19 05:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 156145.72, '2024-01-19 06:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 30743.95, '2024-01-19 06:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1344.75, '2024-01-19 06:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 5178.21, '2024-01-19 06:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 162147.57, '2024-01-19 07:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 34903.48, '2024-01-19 07:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1083.93, '2024-01-19 07:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 5443.20, '2024-01-19 07:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 140505.84, '2024-01-19 08:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 31740.41, '2024-01-19 08:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1023.38, '2024-01-19 08:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 6385.05, '2024-01-19 08:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 167467.09, '2024-01-19 09:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 28654.37, '2024-01-19 09:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 873.36, '2024-01-19 09:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 5916.30, '2024-01-19 09:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 182520.60, '2024-01-19 10:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 26602.57, '2024-01-19 10:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1008.68, '2024-01-19 10:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 6689.00, '2024-01-19 10:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 188139.28, '2024-01-19 11:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 27713.27, '2024-01-19 11:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 847.08, '2024-01-19 11:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 7243.50, '2024-01-19 11:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 179125.09, '2024-01-19 12:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 30831.64, '2024-01-19 12:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 856.19, '2024-01-19 12:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 8433.29, '2024-01-19 12:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 166063.94, '2024-01-19 13:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 29383.81, '2024-01-19 13:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 959.69, '2024-01-19 13:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 8525.17, '2024-01-19 13:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 133182.08, '2024-01-19 14:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 28647.63, '2024-01-19 14:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 858.38, '2024-01-19 14:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 9368.25, '2024-01-19 14:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 132252.41, '2024-01-19 15:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 29242.02, '2024-01-19 15:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 978.92, '2024-01-19 15:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 8374.07, '2024-01-19 15:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 131494.17, '2024-01-19 16:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 25404.65, '2024-01-19 16:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 980.67, '2024-01-19 16:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 9701.49, '2024-01-19 16:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 127564.43, '2024-01-19 17:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 24781.07, '2024-01-19 17:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1062.98, '2024-01-19 17:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 9375.02, '2024-01-19 17:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 145523.14, '2024-01-19 18:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 26452.29, '2024-01-19 18:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1011.47, '2024-01-19 18:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 10857.38, '2024-01-19 18:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 128536.13, '2024-01-19 19:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 28899.79, '2024-01-19 19:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1213.15, '2024-01-19 19:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 10763.02, '2024-01-19 19:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 146871.19, '2024-01-19 20:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 24774.33, '2024-01-19 20:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1097.87, '2024-01-19 20:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 11052.05, '2024-01-19 20:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 137987.40, '2024-01-19 21:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 20794.68, '2024-01-19 21:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1108.08, '2024-01-19 21:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 11353.16, '2024-01-19 21:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 165024.31, '2024-01-19 22:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 24417.95, '2024-01-19 22:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1132.67, '2024-01-19 22:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 10018.87, '2024-01-19 22:00:00');

INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 1, 165584.89, '2024-01-19 23:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (1, 2, 23203.48, '2024-01-19 23:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 3, 1016.82, '2024-01-19 23:00:00');
INSERT INTO History (idPortefeuille, idAction, price, date)
VALUES (2, 4, 9133.51, '2024-01-19 23:00:00');





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



