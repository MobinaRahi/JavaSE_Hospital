package hospital.model.tools;

import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayType;
import hospital.model.service.DoctorService;
import hospital.model.service.PatientService;

import java.sql.ResultSet;

public class PaymentMapper {
    public Payment paymentMapper(ResultSet resultSet) throws Exception {
        return Payment
                .builder()
                .id(resultSet.getInt("id"))
                .payType(PayType.valueOf(resultSet.getString("pay_type")))
                .payDate(resultSet.getDate("pay_date").toLocalDate())
                .price(DoctorService.getService().findById(resultSet.getInt("price")).getPrice())
                .doctor(DoctorService.getService().findById(resultSet.getInt("doctor_id")))
                .patient(PatientService.getService().findById(resultSet.getInt("patient_id")))
                .build();
    }
}
