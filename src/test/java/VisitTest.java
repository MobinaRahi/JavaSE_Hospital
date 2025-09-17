
import hospital.model.entity.Visit;
import hospital.model.service.*;

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

//        Payment payment=
//                Payment
//                        .builder()
//                        .payType(PayType.CARD)
//                        .payDate(LocalDate.now())
//                        .doctor(DoctorService.getService().findById(2))
//                        .price(DoctorService.getService().findById(2).getPrice())
//                        .patient(PatientService.getService().findById(2))
//                        .build();

//        PaymentService.getService().save(payment);

        Visit visit=
                Visit
                        .builder()
                        .id(2)
                        .doctor(DoctorService.getService().findById(2))
                        .patient(PatientService.getService().findById(2))
                        .payment(PaymentService.getService().findById(3))
                        .build();
        VisitService.getService().save(visit);
//        Service test pass

//        test passed
//

//        test passed
//        VisitService.getService().edit(visit);

//        test passed
//        VisitService.getService().delete(2);

//        test passed
//        System.out.println(VisitService.getService().findAll());

//        test passed
//        System.out.println(VisitService.getService().findById(4));

    }
}
