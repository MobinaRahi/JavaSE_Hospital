package hospital.model.repository;
import hospital.model.entity.DrugStock;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.DrugStockMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugStockRepository implements Repository<DrugStock, Integer>,AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final DrugStockMapper drugStockMapper= new DrugStockMapper();

    public DrugStockRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save (DrugStock drugStock) throws Exception {
        drugStock.setId(ConnectionProvider.getProvider().getNextId("drug_stock_seq"));

        preparedStatement = connection.prepareStatement(
                "insert into drugs_stock (id,drug_id, drug_count) values (?, ?,?)"
        );
        preparedStatement.setInt(1, drugStock.getId());
        preparedStatement.setInt(2, drugStock.getDrug().getId());
        preparedStatement.setInt(3, drugStock.getDrugCount());
        preparedStatement.execute();

    }


    @Override
    public void edit (DrugStock drugStock) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update drugs_stock set drug_id=?,drug_count = ? where id = ?"
        );
        preparedStatement.setInt(1,drugStock.getDrug().getId());
        preparedStatement.setInt(2, drugStock.getDrugCount());
        preparedStatement.setInt(3, drugStock.getId());
        preparedStatement.execute();

    }

    @Override
    public void delete (Integer id) throws Exception {

        preparedStatement = connection.prepareStatement(
                "delete from drugs_stock where id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();


    }

    @Override
    public List<DrugStock> findAll ( ) throws Exception {
        List<DrugStock> drugStockList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from drugs_stock order by drug_count");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            DrugStock drugStock=drugStockMapper .drugStockMapper(resultSet);
            drugStockList.add(drugStock);
        }
        return drugStockList;
    }

    @Override
    public DrugStock findById (Integer id) throws Exception {
        DrugStock drugStock = null;
        preparedStatement = connection.prepareStatement("select * from drugs_stock where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            drugStock = drugStockMapper.drugStockMapper(resultSet);
        }
        return drugStock;
    }

    @Override
    public void close ( ) throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
