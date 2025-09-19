import hospital.model.entity.*;
import hospital.model.entity.enums.PayType;
import hospital.model.entity.enums.Role;
import hospital.model.entity.enums.Specialty;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.*;
import hospital.model.tools.MedicalMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicalTest {
    public static void main(String[] args) throws Exception {

//        User user=
//                User
//                        .builder()
//                        .name("test")
//                        .family("test")
//                        .password("test")
//                        .birthDate(LocalDate.of(1980, 1, 1))
//                        .role(Role.DOCTOR)
//                        .status(true)
//                        .username("test")
//                        .nickname("test")
//                        .locked(true)
//                        .registerDate(LocalDate.now())
//                        .build();
//
//        UserService.getService().save(user);

//        Doctor doctor =
//                Doctor
//                        .builder()
//                        .user(UserService.service.findById(2))
//                        .specialty(Specialty.dermatologist)
//                        .price(VisitPrice.VISIT2.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor);

//        Patient patient=
//                Patient
//                        .builder()
//                        .user(UserService.getService().findById(2))
//                        .build();
//
//        PatientService.getService().save(patient);

        Medical medical =
                Medical
                        .builder()
                        .id(3)
                        .title("test")
                        .description("test")
                        .duration(160)
                        .doctor(DoctorService.service.findById(1))
                        .price(20000)
                        .build();


//        Service test pass

//        test passed
        MedicalService.getService().save(medical);

//        test passed
//        MedicalService.getService().edit(medical);

//        test passed
//        MedicalService.getService().delete(3);

//        test passed
//        System.out.println(MedicalService.getService().findAll());

//        test passed
//        System.out.println(MedicalService.getService().findById(4));

//        test passed
//        System.out.println(MedicalService.getService().findMedicalByDoctorId(1));





    }
}
