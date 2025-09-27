package hospital.model.repository;

import hospital.model.entity.Medical;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.MedicalMapper;
import lombok.extern.log4j.Log4j2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MedicalRepository implements Repository<Medical, Integer>, AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final MedicalMapper medicalMapper = new MedicalMapper();

    public MedicalRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Medical medical) throws Exception {
        medical.setId(ConnectionProvider.getProvider().getNextId("medical_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into medicals (id,title,description,duration,doctor_id,price) values(?,?,?,?,?,?)"
        );

        preparedStatement.setInt(1, medical.getId());
        preparedStatement.setString(2, medical.getTitle());
        preparedStatement.setString(3, medical.getDescription());
        preparedStatement.setInt(4, medical.getDuration());
        preparedStatement.setInt(5,medical.getDoctor().getId());
        preparedStatement.setFloat(6, medical.getPrice());
        preparedStatement.execute();
        log.info("Medical save success.");
    }

    @Override
    public void edit(Medical medical) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update medicals set title=?,description=?,duration=?,doctor_id=?,price=? where id=?"
        );
        preparedStatement.setString(1, medical.getTitle());
        preparedStatement.setString(2, medical.getDescription());
        preparedStatement.setInt(3, medical.getDuration());
        preparedStatement.setInt(4,medical.getDoctor().getId());
        preparedStatement.setFloat(5, medical.getPrice());
        preparedStatement.setInt(6, medical.getId());
        preparedStatement.execute();
        log.info("Medical edit success.");
        System.out.println("Medical edit success.");
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
        List<Medical> medicalList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from medicals"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Medical medical = medicalMapper.medicalMapper(resultSet);
            medicalList.add(medical);
        }
        return medicalList;
    }

    @Override
    public Medical findById(Integer id) throws Exception {
        Medical medical = null;
        preparedStatement = connection.prepareStatement(
                "select * from medicals where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            medical = medicalMapper.medicalMapper(resultSet);
        }
        return medical;
    }

    public List<Medical> findByDoctorId(Integer doctorId) throws Exception {
        List<Medical> medicalList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from medicals where doctor_id=?"
        );
        preparedStatement.setInt(1, doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Medical medical = medicalMapper.medicalMapper(resultSet);
            medicalList.add(medical);
        }
        return medicalList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
