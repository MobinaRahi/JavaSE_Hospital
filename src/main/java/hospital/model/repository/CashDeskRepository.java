package hospital.model.repository;

import hospital.model.entity.CashDesk;
import hospital.model.tools.CashDeskMapper;
import hospital.model.tools.ConnectionProvider;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashDeskRepository implements Repository<CashDesk, Integer>,AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final CashDeskMapper cashDeskMapper = new CashDeskMapper();

    public CashDeskRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save (CashDesk cashDesk) throws Exception {
        cashDesk.setId(ConnectionProvider.getProvider().getNextId("cashDesk_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into cashDesks (id, cashBalance,bankBalance ) values (?, ?, ?)"
        );
        preparedStatement.setInt(1, cashDesk.getId());
        preparedStatement.setFloat(2, cashDesk.getCashBalance());
        preparedStatement.setFloat(2, cashDesk.getBankBalance());
        preparedStatement.execute();
    }


    @Override
    public void edit (CashDesk cashDesk) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update cashDesks set id = ?, cashBalance = ?, qubankBalanceantity = ?"
        );
        preparedStatement.setInt(1, cashDesk.getId());
        preparedStatement.setFloat(2, cashDesk.getCashBalance());
        preparedStatement.setFloat(3, cashDesk.getCashBalance());
        preparedStatement.execute();
    }



    @Override
    public void delete (Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from cashDecks where id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    @Override
    public List<CashDesk> findAll ( ) throws Exception {
        List<CashDesk> cashDeskArrayList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from cashDecks order by id,cashBalance");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            CashDesk cashDesk= cashDeskMapper.cashDeskMapper(resultSet);
            cashDeskArrayList.add(cashDesk);
        }
        return cashDeskArrayList;
    }

    @Override
    public CashDesk findById (Integer id) throws Exception {
        CashDesk cashDesk = null;
        preparedStatement = connection.prepareStatement("select * from cashDesks where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            cashDesk = cashDeskMapper.cashDeskMapper(resultSet);
        }
        return cashDesk;
    }


    @Override
    public void close ( ) throws Exception {
        preparedStatement.close();
        connection.close();
    }
}




