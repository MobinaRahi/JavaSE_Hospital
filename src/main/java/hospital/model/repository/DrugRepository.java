package hospital.model.repository;

import hospital.model.entity.Drug;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.DrugMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugRepository implements Repository<Drug, Integer>,AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final DrugMapper drugMapper = new DrugMapper();

    public DrugRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }
    @Override
    public void save (Drug drug) throws Exception {
        drug.setId(ConnectionProvider.getProvider().getNextId("DRUG_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO DRUGS (ID, STOCK_ID, NAME, PRICE, QUANTITY) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, drug.getId());
        preparedStatement.setInt(2, drug.getDrugStock().getId());
        preparedStatement.setString(3, drug.getName());
        preparedStatement.setDouble(4, drug.getPrice());
        preparedStatement.setInt(5, drug.getQuantity());
        preparedStatement.execute();
    }


    @Override
    public void edit (Drug drug) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE DRUGS SET NAME = ?, PRICE = ?, QUANTITY = ?, WHERE ID = ?"
        );
        preparedStatement.setString(1, drug.getName());
        preparedStatement.setDouble(2, drug.getPrice());
        preparedStatement.setInt(4, drug.getQuantity());
        preparedStatement.setInt(5, drug.getId());
        preparedStatement.execute();
    }



    @Override
    public void delete (Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM DRUGS WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    @Override
    public List<Drug> findAll ( ) throws Exception {
        List<Drug> drugList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM DRUGS ORDER BY NAME,PRICE");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Drug drug= drugMapper.drugMapper(resultSet);
            drugList.add(drug);
        }
        return drugList;
    }

    @Override
    public Drug findById (Integer id) throws Exception {
        Drug drug = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM DRUGS WHERE ID = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            drug = drugMapper.drugMapper(resultSet);
        }
        return drug;
    }


    public List<Drug> findByNameAndPrice(String name, Double price) throws Exception {
        List<Drug> drugList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM DRUGS WHERE NAME LIKE ? AND PRICE LIKE ?"
        );
        preparedStatement.setString(1, name + "%");
        preparedStatement.setDouble(2, Double.parseDouble(price + "%"));
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            drugList.add(drugMapper.drugMapper(resultSet));
        }
        return drugList;
    }

    public List<Drug> findByStockId(int stockId) throws Exception {
        List<Drug> drugList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM DRUGS WHERE STOCK_ID=?"
        );
        preparedStatement.setInt(1, stockId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Drug drug = drugMapper.drugMapper(resultSet);
            drugList.add(drug);
        }
        return drugList;
    }


    @Override
    public void close ( ) throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
