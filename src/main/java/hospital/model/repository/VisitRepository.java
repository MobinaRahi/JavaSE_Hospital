package hospital.model.repository;

import hospital.model.entity.Visit;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.VisitMapper;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class VisitRepository implements Repository<Visit, Integer> ,AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final VisitMapper visitMapper=new VisitMapper();

    public VisitRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Visit visit) throws Exception {
        visit.setId(ConnectionProvider.getProvider().getNextId("visit_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into visits (id,doctor_id,patient_id,time_shift_id,price)values(?,?,?,?,?)"
        );
        preparedStatement.setLong(1, visit.getId());
        preparedStatement.setInt(2, visit.getDoctor().getId());
        preparedStatement.setInt(3, visit.getPatient().getId());
        preparedStatement.setInt(4, visit.getTimeShift().getId());
        preparedStatement.setDouble(5, visit.getPrice());
        preparedStatement.execute();
        log.info("Visit save success.");
    }

    @Override
    public void edit(Visit visit) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update visits set doctor_id=?,patient_id=?,time_shift_id=?,price=? where id=?"
        );
        preparedStatement.setInt(1, visit.getDoctor().getId());
        preparedStatement.setInt(2, visit.getPatient().getId());
        preparedStatement.setInt(3, visit.getTimeShift().getId());
        preparedStatement.setDouble(4, visit.getPrice());
        preparedStatement.setInt(5, visit.getId());
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
        List<Visit> visits = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from visits"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Visit visit = visitMapper.visitmapper(resultSet);
            visits.add(visit);
        }
        return visits;
    }

    @Override
    public Visit findById(Integer id) throws Exception {
        Visit visit = null;
        preparedStatement = connection.prepareStatement(
                "select * from visits where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            visit = visitMapper.visitmapper(resultSet);
        }
        return visit;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
