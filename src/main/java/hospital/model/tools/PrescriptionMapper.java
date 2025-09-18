package hospital.model.tools;

import hospital.model.entity.Prescription;
import hospital.model.service.PaymentService;
import hospital.model.service.VisitService;

import java.sql.ResultSet;

public class PrescriptionMapper {
    public Prescription prescriptionMapper(ResultSet resultSet) throws Exception {
        return Prescription
                .builder()
                .id(resultSet.getInt("id"))
                .visit(VisitService.getService().findById(resultSet.getInt("visit_id")))
                .payment(PaymentService.getService().findById(resultSet.getInt("payment_id")))
                .price(resultSet.getInt("price"))
                .build();
    }
}
