package testServices;

import hospital.model.entity.Patient;
import hospital.model.service.PatientService;
import hospital.model.service.UserService;


public class PatientTestService {
    public static void savePatients() throws Exception {
        Patient patient =
                Patient
                        .builder()
                        .user(UserService.getService().findById(3))
                        .build();

        PatientService.getService().save(patient);
    }
}
