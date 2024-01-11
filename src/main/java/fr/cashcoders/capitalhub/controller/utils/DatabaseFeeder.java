package fr.cashcoders.capitalhub.controller.utils;

//     private final MainView mainView = CapitalHubApp.mainView;
//    private final PortefeuilleDetailsView portefeuilleDetailsView = CapitalHubApp.portefeuilleDetailsView;
//    private final HistoryView historyView = CapitalHubApp.historyView;
//    private Connection connection = DataBaseConnectionSingleton.getInstance().getConnection();
//
//    public Model() {
//        this.portefeuilles = new ArrayList<>();
//        this.history = new HashMap<>();
//
//        DatabaseFeeder.load(portefeuilles, history, currencies, connection);

import fr.cashcoders.capitalhub.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseFeeder {

    private static final Logger logger = Logger.getLogger(DatabaseFeeder.class.getName());

    public static void load(List<Portefeuille> portefeuilles, List<Currency> currencies, Connection connection) throws SQLException {
        logger.info("Loading database...");

        loadPortefeuilles(portefeuilles, connection);
        loadCurrencies(currencies, connection);

        logger.info("Done loading portefeuilles and currencies");
    }

    private static void loadPortefeuilles(List<Portefeuille> portefeuilles, Connection connection) throws SQLException {
        logger.info("Loading portefeuilles...");
        PreparedStatement portefeuilleStatement = connection.prepareStatement("SELECT * FROM portefeuille");
        ResultSet portefeuilleResultSet = portefeuilleStatement.executeQuery();

        while (portefeuilleResultSet.next()) {
            int portefeuilleId = portefeuilleResultSet.getInt("id");
            int userId = portefeuilleResultSet.getInt("iduser");
            String portefeuilleName = portefeuilleResultSet.getString("name");
            String description = portefeuilleResultSet.getString("description");

            Portefeuille portefeuille = new Portefeuille(portefeuilleId, userId, portefeuilleName, description);
            loadActionProduits(portefeuille, connection);
            loadTransactions(portefeuille, connection);
            loadHistory(portefeuille, connection);

            portefeuilles.add(portefeuille);
            logger.info("Done loading data for portefeuille " + portefeuilleName);
        }
    }

    private static void loadActionProduits(Portefeuille portefeuille, Connection connection) throws SQLException {
        logger.info("Loading actionProduits for portefeuille " + portefeuille.getName());
        PreparedStatement actionProduitStatement = connection.prepareStatement("SELECT * FROM actionproduit WHERE idportefeuille = ?");
        actionProduitStatement.setInt(1, portefeuille.getId());
        ResultSet actionProduitResultSet = actionProduitStatement.executeQuery();
        while (actionProduitResultSet.next()) {
            int actionId = actionProduitResultSet.getInt("idaction");
            double quantity = actionProduitResultSet.getDouble("quantity");

            PreparedStatement actionStatement = connection.prepareStatement("SELECT * FROM action WHERE id = ?");
            actionStatement.setInt(1, actionId);
            ResultSet actionResultSet = actionStatement.executeQuery();
            while (actionResultSet.next()) {
                int actionId2 = actionResultSet.getInt("id");
                String actionName = actionResultSet.getString("name");
                double actionValue = actionResultSet.getDouble("value");

                Action action = new Action(actionId2, actionName, actionValue);
                portefeuille.addActionProduit(new ActionProduit(action, portefeuille, quantity));
            }
        }
        logger.info("Done loading actionProduits for portefeuille " + portefeuille.getName());
    }

    private static void loadTransactions(Portefeuille portefeuille, Connection connection) throws SQLException {
        logger.info("Loading transactions for portefeuille " + portefeuille.getName());
        PreparedStatement transactionStatement = connection.prepareStatement("SELECT * FROM transaction WHERE idportefeuille = ?");
        transactionStatement.setInt(1, portefeuille.getId());
        ResultSet transactionResultSet = transactionStatement.executeQuery();
        while (transactionResultSet.next()) {
            int transactionId = transactionResultSet.getInt("id");
            int actionId = transactionResultSet.getInt("idaction");
            double price = transactionResultSet.getDouble("price");
            String codeCurrency = transactionResultSet.getString("codecurrency");
            String type = transactionResultSet.getString("type");
            LocalDateTime date = transactionResultSet.getTimestamp("date").toLocalDateTime();

            PreparedStatement actionStatement = connection.prepareStatement("SELECT * FROM action WHERE id = ?");
            actionStatement.setInt(1, actionId);
            ResultSet actionResultSet = actionStatement.executeQuery();
            while (actionResultSet.next()) {
                int actionId2 = actionResultSet.getInt("id");
                String actionName = actionResultSet.getString("name");
                double actionValue = actionResultSet.getDouble("value");

                Action action = new Action(actionId2, actionName, actionValue);
                portefeuille.addTransaction(new Transaction(transactionId, portefeuille, action, price, date, codeCurrency, TransactionType.fromString(type)));
            }
        }
        logger.info("Done loading transactions for portefeuille " + portefeuille.getName());

    }

    private static void loadHistory(Portefeuille portefeuille, Connection connection) throws SQLException {
        logger.info("Loading history for portefeuille " + portefeuille.getName());
        PreparedStatement historyStatement = connection.prepareStatement("SELECT * FROM history WHERE idportefeuille = ?");
        historyStatement.setInt(1, portefeuille.getId());
        ResultSet historyResultSet = historyStatement.executeQuery();
        while (historyResultSet.next()) {
            int historyId = historyResultSet.getInt("id");
            int actionId = historyResultSet.getInt("idaction");
            double price = historyResultSet.getDouble("price");
            LocalDateTime date = historyResultSet.getTimestamp("date").toLocalDateTime();

            PreparedStatement actionStatement = connection.prepareStatement("SELECT * FROM action WHERE id = ?");
            actionStatement.setInt(1, actionId);
            ResultSet actionResultSet = actionStatement.executeQuery();
            while (actionResultSet.next()) {
                int actionId2 = actionResultSet.getInt("id");
                String actionName = actionResultSet.getString("name");
                double actionValue = actionResultSet.getDouble("value");

                Action action = new Action(actionId2, actionName, actionValue);
                portefeuille.addHistory(new History(historyId, portefeuille, action, price, date));
            }
        }
        logger.info("Done loading history for portefeuille " + portefeuille.getName());
    }


    private static void loadCurrencies(List<Currency> currencies, Connection connection) throws SQLException {
        logger.info("Loading currencies...");
        PreparedStatement currencyStatement = connection.prepareStatement("SELECT * FROM currency");
        ResultSet currencyResultSet = currencyStatement.executeQuery();

        while (currencyResultSet.next()) {
            String code = currencyResultSet.getString("code");
            String name = currencyResultSet.getString("name");
            String symbol = currencyResultSet.getString("symbole");

            Currency currency = new Currency(code, name, symbol);
            currencies.add(currency);
        }
        logger.info("Done loading currencies");
    }


}