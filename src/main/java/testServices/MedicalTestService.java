package testServices;

import hospital.model.entity.Medical;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;

public class MedicalTestService {
    public static void saveMedicals() throws Exception {
        Medical medical =
                Medical
                        .builder()
                        .title("Medical 1")
                        .description("Medical 1")
                        .doctor(DoctorService.getService().findById(4))
                        .duration(60)
                        .price(1500)
                        .build();

        MedicalService.getService().save(medical);

        Medical medical2 =
                Medical
                        .builder()
                        .title("Medical 1")
                        .description("Medical 1")
                        .doctor(DoctorService.getService().findById(2))
                        .duration(30)
                        .price(1500)
                        .build();
//
        MedicalService.getService().save(medical2);
    }
}
