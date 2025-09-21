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
        drug.setId(ConnectionProvider.getProvider().getNextId("drug_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into drugs (id, name, price, quantity) values (?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, drug.getId());
        preparedStatement.setString(2, drug.getName());
        preparedStatement.setDouble(3, drug.getPrice());
        preparedStatement.setInt(4, drug.getQuantity());
        preparedStatement.execute();
    }


    @Override
    public void edit (Drug drug) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update drugs set name = ?, price = ?, quantity = ? where id = ?"
        );
        preparedStatement.setString(1, drug.getName());
        preparedStatement.setDouble(2, drug.getPrice());
        preparedStatement.setInt(3, drug.getQuantity());
        preparedStatement.setInt(4, drug.getId());
        preparedStatement.execute();
    }



    @Override
    public void delete (Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from drugs where id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    @Override
    public List<Drug> findAll ( ) throws Exception {
        List<Drug> drugList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from drugs order by name,price");
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
        preparedStatement = connection.prepareStatement("select * from drugs where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            drug = drugMapper.drugMapper(resultSet);
        }
        return drug;
    }


    public List<Drug> findByName(String name) throws Exception {
        List<Drug> drugList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from drugs where name like ?"
        );
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            drugList.add(drugMapper.drugMapper(resultSet));
        }
        return drugList;
    }

    public List<Drug> findByPrescriptionId(int prescriptionId) throws Exception {
        List<Drug> drugList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from PRESCRIPTIONS_DRUGS where DRUG_ID=?"
        );
        preparedStatement.setInt(1, prescriptionId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            drugList.add(drugMapper.drugMapper(resultSet));
        }
        return drugList;
    }


    @Override
    public void close ( ) throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
