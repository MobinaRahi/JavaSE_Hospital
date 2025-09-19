package hospital.model.repository;

import hospital.model.entity.Bank;
import hospital.model.tools.BankMapper;
import hospital.model.tools.ConnectionProvider;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankRepository implements Repository<Bank, Integer>,AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final BankMapper bankMapper = new BankMapper();

    public BankRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }
    @Override
    public void save (Bank bank) throws Exception {
        bank.setId(ConnectionProvider.getProvider().getNextId("bank_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into banks (id, title) values ( ?, ?)"
        );
        preparedStatement.setInt(1, bank.getId());
        preparedStatement.setString(2, bank.getTitle());
        preparedStatement.execute();
    }


    @Override
    public void edit (Bank bank) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update banks set id = ?, title = ?"
        );
        preparedStatement.setInt(1, bank.getId());
        preparedStatement.setString(2, bank.getTitle());
        preparedStatement.execute();
    }



    @Override
    public void delete (Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from banks where id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    @Override
    public List<Bank> findAll ( ) throws Exception {
        List<Bank> bankList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from banks order by id,title");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Bank bank= bankMapper.bankMapper(resultSet);
            bankList.add(bank);
        }
        return bankList;
    }

    @Override
    public Bank findById (Integer id) throws Exception {
        Bank bank = null;
        preparedStatement = connection.prepareStatement("select * from banks where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            bank = bankMapper.bankMapper(resultSet);
        }
        return bank;
    }





    @Override
    public void close ( ) throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
