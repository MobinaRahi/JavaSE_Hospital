package hospital.model.repository;

import hospital.model.entity.Patient;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.PatientMapper;
import lombok.extern.log4j.Log4j2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PatientRepository implements Repository<Patient, Integer>, AutoCloseable{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PatientMapper patientMapper = new PatientMapper();

    public PatientRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Patient patient) throws Exception {
        patient.setId(ConnectionProvider.getProvider().getNextId("patients_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into Patients (id,user_id) values(?,?)"
        );
        preparedStatement.setInt(1, patient.getId());
        preparedStatement.setInt(2, patient.getUser().getId());
        preparedStatement.execute();
    }

    @Override
    public void edit(Patient patient) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update patients set user_id=? where id=?"
        );
        preparedStatement.setInt(1, patient.getUser().getId());
        preparedStatement.setInt(2, patient.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Patients where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Patient> findAll() throws Exception {
        List<Patient> patientList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from Patients"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Patient patient = patientMapper.patientMapper(resultSet);
            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public Patient findById(Integer id) throws Exception {
        Patient patient = null;
        preparedStatement = connection.prepareStatement(
                "select * from Patients where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            patient = patientMapper.patientMapper(resultSet);
        }
        return patient;
    }

    public List<Patient> findByUserId(Integer userId) throws Exception {
        List<Patient> patientList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from Patients where user_id=?"
        );
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Patient patient = patientMapper.patientMapper(resultSet);
            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
