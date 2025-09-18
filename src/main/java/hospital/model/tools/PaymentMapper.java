package hospital.model.tools;

import hospital.model.entity.Payable;
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
                .payDateTime(resultSet.getTimestamp("pay_date_time").toLocalDateTime())
                .price(resultSet.getFloat("price"))
                .payable((Payable) resultSet.getObject("payable"))
                .build();
    }
}
