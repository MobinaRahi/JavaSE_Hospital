package hospital.model.tools;

import hospital.model.entity.Prescription;
import hospital.model.service.DrugService;
import hospital.model.service.VisitService;

import java.sql.ResultSet;

public class PrescriptionMapper {
    public Prescription prescriptionMapper(ResultSet prescriptionResultSet, ResultSet drugListResultSet) throws Exception {
        Prescription Prescription = hospital.model.entity.Prescription
                .builder()
                .id(prescriptionResultSet.getInt("id"))
                .visit(VisitService.getService().findById(prescriptionResultSet.getInt("visit_id")))
                .price(prescriptionResultSet.getInt("price"))
                .build();

        Prescription.setDrugList(DrugService.getService().findByPrescriptionId(Prescription.getId()));
        return Prescription;
    }
}
