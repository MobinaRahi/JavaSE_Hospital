package hospital.model.tools;


import hospital.model.entity.Patient;
import hospital.model.service.PrescriptionService;
import hospital.model.service.UserService;
import hospital.model.service.VisitService;

import java.sql.ResultSet;

public class PatientMapper {
    public Patient patientMapper(ResultSet resultSet) throws Exception {
        return Patient
                .builder()
                .id(resultSet.getInt("id"))
                .user(UserService.getService().findById(resultSet.getInt("user_id")))
                .visit(VisitService.getService().findById(resultSet.getInt("visit_id")))
                .prescription(PrescriptionService.getService().findById(resultSet.getInt("prescription_id")))
                .build();
    }
}
