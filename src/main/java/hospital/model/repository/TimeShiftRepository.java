package hospital.model.repository;

import hospital.model.entity.TimeShift;
import hospital.model.tools.ConnectionProvider;
import lombok.extern.log4j.Log4j2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Log4j2
public class TimeShiftRepository implements Repository<TimeShift,Integer>,AutoCloseable{

    private Connection connection;
    private PreparedStatement preparedStatement;

    public TimeShiftRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }
    @Override
    public void save(TimeShift timeShift) throws Exception {
        timeShift.setId(ConnectionProvider.getProvider().getNextId("time_shift_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into timeShifts (id,doctor_id,start_date_time,end_date_time)values(time_shift_seq.nextval,?,?,?) "
        );
        preparedStatement.setInt(1, timeShift.getDoctor().getId());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(timeShift.getStartDateTime()));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(timeShift.getEndDateTime()));
        preparedStatement.execute();
        log.info("TimeShift save success.");
    }

    @Override
    public void edit(TimeShift timeShift) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update timeShifts set doctor_id=? ,start_date_time=? ,end_date_time=? where id=?"
        );
        preparedStatement.setInt(1, timeShift.getDoctor().getId());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(timeShift.getStartDateTime()));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(timeShift.getEndDateTime()));
        preparedStatement.setInt(4, timeShift.getId());
        preparedStatement.execute();
        log.info("TimeShift edit success.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from timeShifts where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("TimeShift delete success.");
    }

    @Override
    public List<TimeShift> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public TimeShift findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
