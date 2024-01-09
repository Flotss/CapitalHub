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
//        LoadDataBase.load(portefeuilles, history, currencies, connection);

import fr.cashcoders.capitalhub.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class LoadDataBase {

    public static void load(List<Portefeuille> portefeuilles, Map<Portefeuille, History> history, List<Currency> currencies, Connection connection) throws SQLException {
        // Research in database all portefeuilles
        // For each portefeuille, create a new Portefeuille object
        // Add it to the list of portefeuilles


        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM portefeuille");
        preparedStatement.executeQuery();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            Portefeuille portefeuille = new Portefeuille(resultSet.getInt("id"),
                    resultSet.getInt("iduser"),
                    resultSet.getString("name"),
                    resultSet.getString("description"));
            portefeuilles.add(portefeuille);

            // Research in database all transactions
            // For each transaction, create a new Transaction object
            // Add it to the portefeuille
            PreparedStatement preparedStatement2 = connection.prepareStatement("select DISTINCT (a.id) as id, a.name as name, value  from Portefeuille p join Transaction t on p.id = t.idPortefeuille join Action a on t.idAction = a.id where p.id = ?;");
            preparedStatement2.setInt(1, portefeuille.getId());
            preparedStatement2.executeQuery();
            ResultSet resultSet2 = preparedStatement2.getResultSet();
            while (resultSet2.next()) {
                portefeuille.addAction(new Action(resultSet2.getInt("id"),
                        resultSet2.getString("name"),
                        resultSet2.getDouble("value")));

                preparedStatement2 = connection.prepareStatement("select * from Transaction where idPortefeuille = ? and idAction = ?;");
                preparedStatement2.setInt(1, portefeuille.getId());
                preparedStatement2.setInt(2, resultSet2.getInt("id"));
                preparedStatement2.executeQuery();
                ResultSet resultSet3 = preparedStatement2.getResultSet();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                while (resultSet3.next()) {
                    portefeuille.addTransaction(new Transaction(resultSet3.getInt("id"),
                            resultSet3.getInt("idportefeuille"),
                            resultSet3.getInt("idaction"),
                            resultSet3.getDouble("price"),
                            LocalDateTime.parse(resultSet3.getString("date"), formatter),
                            resultSet3.getString("codecurrency"),
                            TransactionType.fromString(resultSet3.getString("type"))));
                }
            }

        }

        // Research in database all currencies
        // For each currency, create a new Currency object
    }

}
