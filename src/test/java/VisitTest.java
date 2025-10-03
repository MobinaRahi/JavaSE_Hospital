
import hospital.model.entity.Visit;
import hospital.model.service.*;

public class VisitTest {
    public static void main(String[] args) throws Exception {

//        Doctor doctor =
//                Doctor
//                        .builder()
//                        .user(UserService.service.findById(2))
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
//                        .password("test4")
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
                        .id(4)
                        .doctor(DoctorService.getService().findById(4))
                        .patient(PatientService.getService().findById(1))
                        .timeShift(TimeShiftService.getService().findById(1))
                        .price(1500)
                        .build();

//        Service test pass

//        test passed
        VisitService.getService().save(visit);

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
