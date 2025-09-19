import hospital.model.entity.Payable;
import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayFor;
import hospital.model.entity.enums.PayType;
import hospital.model.service.DoctorService;
import hospital.model.service.PaymentService;
import hospital.model.service.VisitService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentTest {
    public static void main (String[] args) throws Exception {
//        Doctor doctor =
//                Doctor
//                        .builder()
//                        .specialty(Specialty.dentist)
//                        .price(VisitPrice.VISIT1.getPrice())
//                        .build();
//        DoctorService.getService().save(doctor);



//        User user=
//                User
//                .builder()
//                        .name("test")
//                        .family("test")
//                        .birthDate(LocalDate.of(1980, 1, 1))
//                        .role(Role.DOCTOR)
//                        .status(true)
//                        .username("test3")
//                        .password("test")
//                        .nickname("test")
//                        .locked(false)
//                        .registerDate(LocalDate.now())
//                .build();
//        UserService.getService().save(user);

//        Patient patient =
//                Patient
//                        .builder()
//                        .user(UserService.getService().findById(1))
//                        .build();
//
//        PatientService.getService().save(patient);

        Payment payment=
                Payment
                        .builder()
                        .payType(PayType.CASH)
                        .payDateTime(LocalDateTime.now())
                        .price(1500)
                        .payFor(PayFor.Visit)
                        .payable(VisitService.getService().findById(3))
                        .build();

        PaymentService.getService().save(payment);

        //        Service test pass

        //        test passed
//

//        test passed
//        PaymentService.getService().edit(payment);

//        test passed
//        PaymentService.getService().delete(2);

//        test passed
//        System.out.println(PaymentService.getService().findAll());

//        test passed
//        System.out.println(PaymentService.getService().findById(3));

    }
}
