package hospital.model.tools;

import hospital.model.entity.TimeShift;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;

import java.sql.ResultSet;


public class TimeShiftMapper {
    public TimeShift timeShiftMapper(ResultSet resultSet) throws Exception {
        return TimeShift
                .builder()
                .id(resultSet.getInt("id"))
                .doctor(DoctorService.getService().findById(resultSet.getInt("doctor_id")))
                .medical(MedicalService.getService().findById(resultSet.getInt("medical_id")))
                .startDateTime(resultSet.getTimestamp("start_date_time").toLocalDateTime())
                .endDateTime(resultSet.getTimestamp("end_date_time").toLocalDateTime())
                .build();
    }
}
