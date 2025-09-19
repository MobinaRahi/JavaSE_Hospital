import hospital.model.entity.Patient;
import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.service.PatientService;
import hospital.model.service.UserService;

import java.time.LocalDate;

public class PatientTest {
    public static void main(String[] args) throws Exception {

        User user =
                User
                        .builder()
                        .name("sara")
                        .family("moradi")
                        .birthDate(LocalDate.of(2000, 1, 1))
                        .role(Role.DOCTOR)
                        .status(true).username("sara")
                        .password("sara123")
                        .nickname("sar sar")
                        .locked(true)
                        .registerDate(LocalDate.of(2025, 8, 25))
                        .build();

        Patient patient =
                Patient
                        .builder()
                        .id(1)
                        .user(UserService.getService().findById(1))
                        .build();


        //test passed
        //PatientService.getService().save(patient);

        //test passed
        //PatientService.getService().edit(patient);

        //test passed
        //PatientService.getService().delete(patient.getId());

        //test passed
        //System.out.println(PatientService.getService().findById(3));

        //test passed
        //System.out.println(PatientService.getService().findAll());

    }
}
