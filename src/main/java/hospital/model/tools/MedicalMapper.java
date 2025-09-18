package hospital.model.tools;

import hospital.model.entity.Medical;
import hospital.model.service.DoctorService;
import hospital.model.service.PaymentService;

import java.sql.ResultSet;


public class MedicalMapper {
    public Medical medicalMapper(ResultSet resultSet) throws Exception {
        return Medical
                .builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .doctor(DoctorService.getService().findById(resultSet.getInt("doctor_id")))
                .duration(resultSet.getInt("duration"))
                .price(resultSet.getInt("price"))
                .build();

    }
}
