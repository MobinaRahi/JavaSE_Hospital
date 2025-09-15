package hospital.model.repository;

import hospital.model.entity.Visit;
import hospital.model.tools.ConnectionProvider;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Log4j2
public class VisitRepository implements Repository<Visit, Integer> ,AutoCloseable{
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public VisitRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Visit visit) throws Exception {
        visit.setId(ConnectionProvider.getProvider().getNextId("visit_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into visits (id,doctor_id,patient_id,payment_id)values(visit_seq.nextval,?,?,?)"
        );
        preparedStatement.setInt(1, visit.getDoctor().getId());
        preparedStatement.setInt(2, visit.getPatient().getId());
        preparedStatement.setInt(3, visit.getPayment().getId());
        preparedStatement.execute();
        log.info("Visit save success.");
    }

    @Override
    public void edit(Visit visit) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update visits set doctor_id=?,patient_id=?,payment_id=? where id=?"
        );
        preparedStatement.setInt(1, visit.getDoctor().getId());
        preparedStatement.setInt(2, visit.getPatient().getId());
        preparedStatement.setInt(3, visit.getPayment().getId());
        preparedStatement.setInt(4, visit.getId());
        preparedStatement.execute();
        log.info("Visit edite success.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("delete from visits where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Visit delete success.");
    }

    @Override
    public List<Visit> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Visit findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
