import hospital.model.entity.Doctor;
import hospital.model.entity.Medical;
import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayType;
import hospital.model.entity.enums.Specialty;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;


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

        Medical medical =
                Medical
                        .builder()
                        .title("Medical Test")
                        .description("Medical Test")
                        .duration(12.5F)
                        .payment(1)
                        .build();
        MedicalService.getService().save(medical);

    }
}
