import hospital.model.entity.Doctor;
import hospital.model.entity.Medical;
import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayType;
import hospital.model.entity.enums.Specialty;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;
import hospital.model.service.PatientService;
import hospital.model.service.PaymentService;
import hospital.model.tools.MedicalMapper;

import java.time.LocalDate;

public class MedicalTest {
    public static void main(String[] args) throws Exception {
//        Doctor doctor =
//                Doctor
//                        .builder()
//                        .specialty(Specialty.dermatologist)
//                        .price(VisitPrice.VISIT2.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor);

        Payment payment=
                Payment
                        .builder()
                        .payType(PayType.CASH)
                        .payDate(LocalDate.now())
                        .price(DoctorService.getService().findById(2).getPrice())
                        .doctor(DoctorService.getService().findById(2))
                          .patient(PatientService.getService().findById(2))
                        .build();

        PaymentService.getService().save(payment);

//        Medical medical =
//                Medical
//                        .builder()
//                        .id(3)
//                        .title("test")
//                        .description("test")
//                        .duration(160)
//                        .doctor(DoctorService.service.findById(22))
//                        .payment(PaymentService.getService().findById(3))
//                        .build();


//        Service test pass

//        test passed
//        MedicalService.getService().save(medical);

//        test passed
//        MedicalService.getService().edit(medical);

//        test passed
//        MedicalService.getService().delete(3);

//        test passed
//        System.out.println(MedicalService.getService().findAll());

//        test passed
//        System.out.println(MedicalService.getService().findById(5));

//        test passed
//        System.out.println(MedicalService.getService().findMedicalByDoctorId(22));



    }
}
