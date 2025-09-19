package hospital.model.repository;

import hospital.model.entity.TimeShift;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.TimeShiftMapper;
import lombok.extern.log4j.Log4j2;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TimeShiftRepository implements Repository<TimeShift, Integer>, AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final TimeShiftMapper timeShiftMapper = new TimeShiftMapper();

    public TimeShiftRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(TimeShift timeShift) throws Exception {
        timeShift.setId(ConnectionProvider.getProvider().getNextId("time_shift_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into time_Shifts (id,doctor_id,medical_id,start_date_time,end_date_time)values(?,?,?,?,?) "
        );
        preparedStatement.setInt(1,timeShift.getId());
        preparedStatement.setInt(2, timeShift.getDoctor().getId());
        preparedStatement.setInt(3, timeShift.getMedical().getId());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(timeShift.getStartDateTime()));
        preparedStatement.setTimestamp(5, Timestamp.valueOf(timeShift.getEndDateTime()));
        preparedStatement.execute();
        log.info("TimeShift save success.");
    }

    @Override
    public void edit(TimeShift timeShift) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update time_Shifts set doctor_id=?,medical_id=?,start_date_time=? ,end_date_time=? where id=?"
        );
        preparedStatement.setInt(1, timeShift.getDoctor().getId());
        preparedStatement.setInt(2, timeShift.getMedical().getId());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(timeShift.getStartDateTime()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(timeShift.getEndDateTime()));
        preparedStatement.setInt(5, timeShift.getId());
        preparedStatement.execute();
        log.info("TimeShift edit success.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from time_Shifts where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("TimeShift delete success.");
    }

    @Override
    public List<TimeShift> findAll() throws Exception {
        List<TimeShift> timeShiftList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from time_Shifts"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            TimeShift timeShift = timeShiftMapper.timeShiftMapper(resultSet);
            timeShiftList.add(timeShift);
        }
        return timeShiftList;
    }

    @Override
    public TimeShift findById(Integer id) throws Exception {
        TimeShift timeShift = null;
        preparedStatement = connection.prepareStatement(
                "select * from time_Shifts where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            timeShift = timeShiftMapper.timeShiftMapper(resultSet);
        }
        return timeShift;
    }

    public TimeShift findTimeShiftByDoctorId(int doctorId) throws Exception {
        TimeShift timeShift = null;
        preparedStatement = connection.prepareStatement(
                "select * from time_Shifts where doctor_id=?"
        );
        preparedStatement.setInt(1, doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            timeShift = timeShiftMapper.timeShiftMapper(resultSet);
        }
        return timeShift;
    }

    public TimeShift findTimeShiftByMedicalId(int medicalId) throws Exception {
        TimeShift timeShift = null;
        preparedStatement = connection.prepareStatement(
                "select * from time_Shifts where medical_id=?"
        );
        preparedStatement.setInt(1, medicalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            timeShift = timeShiftMapper.timeShiftMapper(resultSet);
        }
        return timeShift;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
