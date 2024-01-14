# CapitalHub - Gestion de Portefeuilles d'Actions et de Crypto

## Introduction
CapitalHub est une application JavaFX conçue pour la gestion de portefeuilles d'actions et de crypto-monnaies. Elle offre une interface utilisateur conviviale pour créer, suivre et gérer des portefeuilles d'investissements, des actions et des données de crypto-monnaies, ainsi que des graphiques pour visualiser les performances.

## Structure du Projet
Le projet est organisé comme suit :

- **sql**: Contient les fichiers SQL pour la création de la base de données et l'insertion de données de test.
- **src/main/java/fr/cashcoders/capitalhub**: Le code source principal de l'application.
  - **controller**: Les contrôleurs JavaFX pour gérer les vues et la logique de l'application.
  - **aggregator**: Les classes responsables de l'agrégation de données.
  - **filter**: Les classes de filtrage des données.
  - **utils**: Les utilitaires, y compris la gestion des API et la sécurité.
  - **database**: La gestion de la base de données et la connexion.
  - **model**: Les classes de modèle pour les données de l'application.
  - **view**: Les vues JavaFX de l'application.
- **resources/fr/cashcoders/capitalhub/view**: Les fichiers FXML pour la conception des interfaces utilisateur.

## Utilisation
1. Clonez le projet depuis le référentiel.
2. Exécutez `Application.java` pour démarrer l'application.

ou bien

Exécuter le fichier .jar qui se trouve dans `jar/Capital-hub-1.0.jar`

## Fonctionnalités
- Création et gestion de portefeuilles d'actions et de crypto-monnaies.
- Visualisation de graphiques pour suivre les performances.
- Connexion sécurisée et inscription d'utilisateurs.
- Gestion des transactions.

## Auteurs
Ce projet a été développé par `Florian MANGIN` et `Pierre Massy` dans le cadre du projet du Module II.1102 – Algorithmique et Programmation de l'[ISEP](https://www.isep.fr/).

**Enjoy using CapitalHub!**
