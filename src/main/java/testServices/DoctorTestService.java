package testServices;

import hospital.model.entity.Doctor;
import hospital.model.entity.enums.Specialty;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.DoctorService;
import hospital.model.service.UserService;

public class DoctorTestService {
    public static void saveDoctors() throws Exception {
        Doctor doctor=
                Doctor
                        .builder()
                        .user(UserService.getService().findById(1))
                        .specialty(Specialty.dentist)
                        .price(VisitPrice.VISIT1.getPrice())
                        .build();

        DoctorService.getService().save(doctor);

        Doctor doctor1=
                Doctor
                        .builder()
                        .user(UserService.getService().findById(1))
                        .specialty(Specialty.dentist)
                        .price(VisitPrice.VISIT1.getPrice())
                        .build();

        DoctorService.getService().save(doctor1);

        Doctor doctor2=
                Doctor
                        .builder()
                        .user(UserService.getService().findById(1))
                        .specialty(Specialty.dermatologist)
                        .price(VisitPrice.VISIT1.getPrice())
                        .build();

        DoctorService.getService().save(doctor2);
    }
}
