package fr.cashcoders.capitalhub.controller.utils;

import fr.cashcoders.capitalhub.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static fr.cashcoders.capitalhub.model.DBInterface.connection;

/**
 * The DatabaseFeeder class provides methods for loading data into the database, including user portefeuilles,
 * currencies, actions, transactions, and history records.
 * It is responsible for populating the database with initial data.
 */
public class DatabaseFeeder {

    private static final Logger logger = Logger.getLogger(DatabaseFeeder.class.getName());
    private static final List<Action> actions = new ArrayList<>();


    /**
     * Loads data into the database, including user portefeuilles, currencies, actions, transactions, and history records.
     *
     * @param user          The user for whom data is loaded.
     * @param portefeuilles A list of Portefeuille objects to be loaded into the database.
     * @param currencies    A list of Currency objects to be loaded into the database.
     * @param connection    The database connection to be used for loading data.
     * @throws SQLException If an SQL exception occurs during the data loading process.
     */
    public static void load(User user, List<Portefeuille> portefeuilles, List<Currency> currencies, Connection connection) throws SQLException {
        logger.info("Loading database...");

        loadPortefeuilles(user, portefeuilles, connection);
        loadCurrencies(currencies, connection);
        loadActions();
        
        
        logger.info("Done loading portefeuilles and currencies");
    }

    /**
     * Loads actions from the database and populates the 'actions' list.
     *
     * @throws SQLException If an SQL exception occurs while loading actions from the database.
     */
    public static void loadActions() throws SQLException {
        logger.info("Loading actions...");
        PreparedStatement actionStatement = connection.prepareStatement("SELECT * FROM action");
        ResultSet actionResultSet = actionStatement.executeQuery();

        while (actionResultSet.next()) {
            int actionId = actionResultSet.getInt("id");
            String actionName = actionResultSet.getString("name");
            double actionValue = actionResultSet.getDouble("value");
            String actionSymbol = actionResultSet.getString("symbol");

            Action action = new Action(actionId, actionName, actionValue, actionSymbol);

            if (!isInActions(action)) {
                actions.add(action);
            }
        }

        logger.info("Done loading actions");
    }


    /**
     * Loads user portefeuilles from the database and populates the 'portefeuilles' list.
     *
     * @param user        The user for whom portefeuilles are loaded.
     * @param portefeuilles A list of Portefeuille objects to be loaded and populated.
     * @param connection  The database connection to be used for loading data.
     * @throws SQLException If an SQL exception occurs while loading portefeuilles from the database.
     */
    private static void loadPortefeuilles(User user, List<Portefeuille> portefeuilles, Connection connection) throws SQLException {
        logger.info("Loading portefeuilles...");
        PreparedStatement portefeuilleStatement = connection.prepareStatement("SELECT * FROM portefeuille WHERE iduser = ?");
        portefeuilleStatement.setInt(1, user.getId());
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

    /**
     * Loads ActionProduits for a given Portefeuille from the database and populates the Portefeuille object.
     *
     * @param portefeuille The Portefeuille object for which ActionProduits are loaded and populated.
     * @param connection  The database connection to be used for loading data.
     * @throws SQLException If an SQL exception occurs while loading ActionProduits from the database.
     */
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
                String actionSymbol = actionResultSet.getString("symbol");

                // Recherche de l'action dans la liste sinon on la crée
                Action action = recoverAction(actionId2, actionName, actionValue, actionSymbol);
                portefeuille.addActionProduit(new ActionProduit(action, portefeuille, quantity));
            }
        }
        logger.info("Done loading actionProduits for portefeuille " + portefeuille.getName());
    }


    /**
     * Recovers an Action object from the 'actions' list if it exists, or creates a new one if not found.
     *
     * @param id    The ID of the Action.
     * @param name  The name of the Action.
     * @param value The value of the Action.
     * @return The recovered or newly created Action object.
     */
    private static Action recoverAction(int id, String name, double value, String symbol) {
        for (Action action : actions) {
            if (action.getId() == id) {
                return action;
            }
        }
        Action action = new Action(id, name, value, symbol);
        actions.add(action);
        return action;
    }


    /**
     * Checks if an Action object is already in the 'actions' list.
     *
     * @param action The Action object to be checked.
     * @return True if the Action is in the list, otherwise false.
     */
    private static boolean isInActions(Action action) {
        for (Action actionItem : actions) {
            if (actionItem.equals(actionItem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads transactions for a given Portefeuille from the database and populates the Portefeuille object.
     *
     * @param portefeuille The Portefeuille object for which transactions are loaded and populated.
     * @param connection   The database connection to be used for loading data.
     * @throws SQLException If an SQL exception occurs while loading transactions from the database.
     */
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
                String actionSymbol = actionResultSet.getString("symbol");

                Action action = recoverAction(actionId2, actionName, actionValue, actionSymbol);
                portefeuille.addTransaction(new Transaction(transactionId, portefeuille, action, price, date, codeCurrency, TransactionType.fromString(type)));
            }
        }
        logger.info("Done loading transactions for portefeuille " + portefeuille.getName());

    }

    /**
     * Loads history records for a given Portefeuille from the database and populates the Portefeuille object.
     *
     * @param portefeuille The Portefeuille object for which history.
     * @param connection   The database connection to be used for loading data.
     * @throws SQLException If an SQL exception occurs while loading history records from the database.
     */
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
                String actionSymbol = actionResultSet.getString("symbol");

                Action action = recoverAction(actionId2, actionName, actionValue, actionSymbol);
                portefeuille.addHistory(new History(historyId, portefeuille, action, price, date));
            }
        }
        logger.info("Done loading history for portefeuille " + portefeuille.getName());
    }


    /**
     * Loads currencies from the database and populates the 'currencies' list.
     *
     * @param currencies A list of Currency objects to be loaded.
     * @param connection The database connection to be used for loading data.
     * @throws SQLException If an SQL exception occurs while loading currencies from the database.
     */
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
