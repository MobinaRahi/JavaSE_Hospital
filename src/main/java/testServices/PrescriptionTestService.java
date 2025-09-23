package testServices;

import hospital.model.entity.Drug;
import hospital.model.entity.Prescription;
import hospital.model.service.PrescriptionService;
import hospital.model.service.VisitService;

import java.util.List;

public class PrescriptionTestService {
    public static void savePrescriptions() throws Exception {
        List<Drug> drugList = DrugTestService.saveDrugs();
        Prescription prescription =
                Prescription
                        .builder()
                        .drugList(drugList)
                        .visit(VisitService.getService().findById(3))
                        .price(12500)
                        .build();

        PrescriptionService.getService().save(prescription);
    }
}

