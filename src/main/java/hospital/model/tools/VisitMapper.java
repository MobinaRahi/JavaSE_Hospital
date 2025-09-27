package hospital.model.tools;

import hospital.model.entity.Visit;
import hospital.model.service.DoctorService;
import hospital.model.service.PatientService;
import hospital.model.service.TimeShiftService;

import java.sql.ResultSet;

public class VisitMapper {
    public Visit visitMapper(ResultSet resultSet) throws Exception {
        return Visit
                .builder()
                .id(resultSet.getInt("id"))
                .doctor(DoctorService.getService().findById(resultSet.getInt("doctor_id")))
                .patient(PatientService.getService().findById(resultSet.getInt("patient_id")))
                .timeShift(TimeShiftService.getService().findById(resultSet.getInt("time_shift_id")))
                .price(resultSet.getDouble("price"))
                .build();
    }
}
