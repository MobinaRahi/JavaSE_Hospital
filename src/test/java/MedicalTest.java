import hospital.model.entity.Doctor;
import hospital.model.entity.Medical;
import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayType;
import hospital.model.entity.enums.Specialty;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;
import hospital.model.service.PaymentService;

import java.time.LocalDate;

public class MedicalTest {
    public static void main(String[] args) throws Exception {
//        Doctor doctor =
//                Doctor
//                        .builder()
//                        .specialty(Specialty.dentist)
//                        .price(VisitPrice.VISIT2.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor);

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

        Medical medical =
                Medical
                        .builder()
                        .title("test")
                        .description("test")
                        .duration(120)
                        .payment(PaymentService.getService().findById(3))
                        .build();
        MedicalService.getService().save(medical);

    }
}
