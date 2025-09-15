package hospital.model.repository;

import hospital.model.entity.Medical;
import hospital.model.tools.ConnectionProvider;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Log4j2
public class MedicalRepository implements Repository<Medical, Integer> {

    private final Connection connection;
    private PreparedStatement preparedStatement;

    public MedicalRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Medical medical) throws Exception {
        medical.setId(ConnectionProvider.getProvider().getNextId("medical_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into medicals (id,title,description,duration,payment_id) values(medical_seq.nextval,?,?,?,?)"
        );
        preparedStatement.setString(1, medical.getTitle());
        preparedStatement.setString(2, medical.getDescription());
        preparedStatement.setInt(3,medical.getPayment().getId());
        preparedStatement.execute();
        log.info("Medical save success.");
    }

    @Override
    public void edit(Medical medical) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update medicals set title=?,description=?,duration=?,payment_id=? where id=?"
        );
        preparedStatement.setString(1, medical.getTitle());
        preparedStatement.setString(2, medical.getDescription());
        preparedStatement.setInt(3,medical.getPayment().getId());
        preparedStatement.setInt(4,medical.getId());
        preparedStatement.execute();
        log.info("Medical edit success.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from medicals where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Medical delete success.");
    }

    @Override
    public List<Medical> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Medical findById(Integer id) throws Exception {
        return null;
    }
}
