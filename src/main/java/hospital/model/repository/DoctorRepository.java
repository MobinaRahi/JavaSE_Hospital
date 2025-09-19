package hospital.model.repository;


import hospital.model.entity.Doctor;
import hospital.model.service.DoctorService;
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
                "insert into doctors (id,user_id,specialty,price) values (?, ? ,?, ?)"
        );
        preparedStatement.setInt(1, doctor.getId());
        preparedStatement.setInt(2, doctor.getUser().getId());
        preparedStatement.setString(3,doctor.getSpecialty().name());
        preparedStatement.setDouble(4, doctor.getPrice());
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
        preparedStatement.execute();
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

    public List<Doctor> findByUserId(Integer userId) throws Exception {
        List<Doctor> doctorList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from doctors where user_id=?"
        );
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Doctor doctor = doctorMapper.doctorMapper(resultSet);
            doctorList.add(doctor);
        }
        return doctorList;
    }

    public List<Doctor>  findBySpecialty(String specialty) throws Exception {
        List<Doctor> doctorList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from doctors where specialty=?"
        );
        preparedStatement.setString(1, specialty);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Doctor doctor = doctorMapper.doctorMapper(resultSet);
            doctorList.add(doctor);
        }
        return doctorList;
    }

    public List<Doctor> findByNameAndFamily(String name, String family) throws Exception {
        List<Doctor> doctorList = new ArrayList<>();
        for (Doctor doctor : DoctorService.getService().findAll()) {
            if(doctor.getUser().getName().equals(name)&&doctor.getUser().getFamily().equals(family)){
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }


    @Override
    public void close() throws Exception {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
