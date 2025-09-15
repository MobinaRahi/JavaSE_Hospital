package hospital.model.repository;


import hospital.model.entity.Doctor;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.DoctorMapper;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DoctorRepository implements Repository<Doctor , Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final DoctorMapper doctorMapper = new DoctorMapper();

    public DoctorRepository() throws Exception{
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Doctor doctor) throws Exception {
        doctor.setId(ConnectionProvider.getProvider().getNextId("doctor_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into doctors (id,specialty,price) values (?, ? ,?)"
        );
        preparedStatement.setInt(1, doctor.getId());
        preparedStatement.setString(2,doctor.getSpecialty().name());
        preparedStatement.setDouble(3, doctor.getPrice());
        preparedStatement.execute();
    }

    @Override
    public void edit(Doctor doctor) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update doctors set specialty=?, price=? where id=?"
        );
        preparedStatement.setString(1, doctor.getSpecialty().name());
        preparedStatement.setDouble(2, doctor.getPrice());
        preparedStatement.setInt(3, doctor.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from doctors where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Doctor> findAll() throws Exception {
        List<Doctor> doctorList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from doctors"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Doctor doctor = doctorMapper.doctorMapper(resultSet);
            doctorList.add(doctor);
        }
        return doctorList;
    }

    @Override
    public Doctor findById(Integer id) throws Exception {
        Doctor doctor = null;
        preparedStatement = connection.prepareStatement(
                "select * from doctors where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            doctor = doctorMapper.doctorMapper(resultSet);
        }
        return doctor;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
