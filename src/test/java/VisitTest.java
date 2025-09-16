import hospital.model.entity.Patient;
import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.service.UserService;

import java.time.LocalDate;

public class VisitTest {
    public static void main(String[] args) throws Exception {

//        Doctor doctor =
//                Doctor
//                        .builder()
//                        .specialty(Specialty.dentist)
//                        .price(VisitPrice.VISIT2.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor);

        User user=
                User
                .builder()
                        .name("test")
                        .family("test")
                        .birthDate(LocalDate.of(1980, 1, 1))
                        .role(Role.DOCTOR)
                        .status(true)
                        .username("test3")
                        .password("test")
                        .nickname("test")
                        .locked(false)
                        .registerDate(LocalDate.now())
                .build();
        UserService.getService().save(user);

        Patient patient =
                Patient
                        .builder()
                        .user(UserService.getService().findById(1))
                        .build();

//        Payment payment=
//                Payment
//                        .builder()
//                        .payType(PayType.CARD)
//                        .payDate(LocalDate.now())
//                        .doctor(DoctorService.getService().findById(2))
//                        .price(DoctorService.getService().findById(2).getPrice())
//                        .build();
//
//        PaymentService.getService().save(payment);

    }
}
