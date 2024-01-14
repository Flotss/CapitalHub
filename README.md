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
2. Exécutez les scripts SQL dans le dossier `sql` pour créer la base de données et insérer des données de test.
3. Configurez la connexion à la base de données dans `DataBaseConnectionSingleton.java`.
4. Exécutez `Application.java` pour démarrer l'application.

## Fonctionnalités
- Création et gestion de portefeuilles d'actions et de crypto-monnaies.
- Visualisation de graphiques pour suivre les performances.
- Connexion sécurisée et inscription d'utilisateurs.
- Gestion des transactions.

## Auteurs
Ce projet a été développé par [Votre Nom] et [Le Nom de Votre Collaborateur] dans le cadre de [Nom du Projet ou de la Cours].

## License
Ce projet est sous licence [Licence], voir le fichier `LICENSE.md` pour plus de détails.

## Remarques
Assurez-vous d'avoir correctement configuré les dépendances JavaFX pour exécuter l'application. Pour toute question ou commentaire, veuillez nous contacter à [votre@email.com].

**Enjoy using CapitalHub!**
