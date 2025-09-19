package hospital.model.repository;

import hospital.model.entity.Prescription;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.PrescriptionMapper;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PrescriptionRepository implements Repository<Prescription, Integer>, AutoCloseable{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    PrescriptionMapper prescriptionMapper = new PrescriptionMapper();

    public PrescriptionRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }


    @Override
    public void save(Prescription prescription) throws Exception {
        prescription.setId(ConnectionProvider.getProvider().getNextId("prescription_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into prescriptions (id,visit_id,price)values (prescription_seq.nextval,?,?)"
        );
        preparedStatement.setInt(1, prescription.getVisit().getId());
        preparedStatement.setDouble(2, prescription.getPrice());
        preparedStatement.execute();
        log.info("Prescription has been saved successfully");
    }

    @Override
    public void edit(Prescription prescription) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update prescriptions set visit_id=?,price=? where id=?"
        );
        preparedStatement.setInt(1, prescription.getVisit().getId());
        preparedStatement.setDouble(2, prescription.getPrice());
        preparedStatement.setInt(3, prescription.getId());
        preparedStatement.execute();
        log.info("Prescription has been edited successfully");
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from prescriptions where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Prescription has been deleted successfully");
    }

    @Override
    public List<Prescription> findAll() throws Exception {
        List<Prescription> prescriptionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from prescriptions"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Prescription prescription = prescriptionMapper.prescriptionMapper(resultSet);
            prescriptionList.add(prescription);
        }
        return prescriptionList;
    }

    @Override
    public Prescription findById(Integer id) throws Exception {
        Prescription prescription = null;
        preparedStatement = connection.prepareStatement(
                "select * from prescriptions where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            prescription = prescriptionMapper.prescriptionMapper(resultSet);
        }
        return prescription;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
