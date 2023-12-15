drop table Event;
drop table History;
drop table Transaction;
drop table TransactionType;
drop table Currency;
drop table Portefeuille;

create table if not exists Portefeuille
(
    idPortefeuille          int          not null,
    nomPortefeuille         varchar(255) not null,
    descriptionPortefeuille varchar(255) not null,
    primary key (idPortefeuille)
);

create table if not exists Marche
(
    idmarche varchar(225) not null,
    nom      varchar(225) not null,
    valeur   int          not null,
    primary key (idmarche)
);

create table if not exists PortefeuilleProduit
(
    idProduit      varchar(225) not null,
    quantité       int          not null,
    idPortefeuille int          not null,
    primary key (idProduit, idPortefeuille),
    foreign key (idPortefeuille) references Portefeuille (idPortefeuille),
    foreign key (idProduit) references Marche (idMarche)
);


create table if not exists Currency
(
    code    varchar(255) not null,
    name    varchar(255) not null,
    symbole varchar(255) not null,
    primary key (symbole)
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
    symbole         varchar(255) not null,
    transactionType varchar(255) not null,
    oldValeur       float        not null,
    newValeur       float        not null,
    primary key (idTransaction),
    foreign key (idPortefeuille) references Portefeuille (idPortefeuille),
    foreign key (symbole) references Currency (symbole),
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
    description   varchar(255) not null,
    primary key (idEvent),
    foreign key (idHistory) references History (idHistory),
    foreign key (idTransaction) references Transaction (idTransaction)
);


select *
from Portefeuille;

insert into Portefeuille (idPortefeuille, nomPortefeuille, descriptionPortefeuille)
values (1, 1, 'Portefeuille 1', 'Description 1');

-- delete all
delete
from Portefeuille
where idUtilisateur = 1;






