package hospital.model.tools;

import hospital.model.entity.Medical;
import hospital.model.service.PaymentService;

import java.sql.ResultSet;


public class MedicalMapper {
    public Medical medicalMapper(ResultSet resultSet) throws Exception {
       return Medical
                        .builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .payment(PaymentService.getService().findById(resultSet.getInt("payment_id")))
                        .build();

    }
}
