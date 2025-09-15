package hospital.model.repository;

import hospital.model.entity.Medical;
import hospital.model.entity.Prescription;
import hospital.model.tools.ConnectionProvider;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Log4j2
public class PrescriptionRepository implements Repository<Prescription, Integer> {

    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PrescriptionRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }


    @Override
    public void save(Prescription prescription) throws Exception {
        prescription.setId(ConnectionProvider.getProvider().getNextId("prescription_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into prescriptions (id,patient_id,visit_id,payment_id,employee_id)values (prescription_seq.nextval,?,?,?,?)"
        );
        preparedStatement.setInt(1, prescription.getPatient().getId());
        preparedStatement.setInt(2, prescription.getVisit().getId());
        preparedStatement.setInt(3, prescription.getPayment().getId());
        preparedStatement.setInt(4, prescription.getEmployee().getId());
        preparedStatement.execute();
        log.info("Prescription has been saved successfully");
    }

    @Override
    public void edit(Prescription prescription) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update prescriptions set patient_id=?,visit_id=?,payment_id=?,employee_id=? where id=?"
        );
        preparedStatement.setInt(1, prescription.getPatient().getId());
        preparedStatement.setInt(2, prescription.getVisit().getId());
        preparedStatement.setInt(3, prescription.getPayment().getId());
        preparedStatement.setInt(4, prescription.getEmployee().getId());
        preparedStatement.setInt(5, prescription.getId());
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
        return Collections.emptyList();
    }

    @Override
    public Prescription findById(Integer id) throws Exception {
        return null;
    }
}
