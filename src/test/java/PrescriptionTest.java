
import hospital.model.entity.Prescription;
import hospital.model.service.*;


public class PrescriptionTest {
    public static void main(String[] args) throws Exception {
//        Payment payment=
//                Payment
//                        .builder()
//                        .payType(PayType.CARD)
//                        .payDate(LocalDate.now())
//                        .doctor(DoctorService.getService().findById(2))
//                        .price(DoctorService.getService().findById(2).getPrice())
//                        .patient(PaymentService.getService().findById(2).getPatient())
//                        .build();
//
//        PaymentService.getService().save(payment);
//
//        Visit visit=
//                Visit
//                        .builder()
//                        .id(2)
//                        .doctor(DoctorService.getService().findById(2))
//                        .patient(PatientService.getService().findById(2))
//                        .payment(PaymentService.getService().findById(3))
//                        .build();

//        VisitService.getService().save(visit);

        Prescription prescription=
                Prescription
                        .builder()
                        .id(2)
                        .visit(VisitService.getService().findById(4))
                        .price(1500)
                        .build();

//        Service test pass

//        test passed
//        PrescriptionService.getService().save(prescription);

//        test passed
//        PrescriptionService.getService().edit(prescription);

//        test passed
//        PrescriptionService.getService().delete(2);

//        test passed
//        System.out.println(PrescriptionService.getService().findAll());

//        test passed
//        System.out.println(PrescriptionService.getService().findById(6));


    }
}
