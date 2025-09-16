package hospital.model.tools;

import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayType;
import java.sql.ResultSet;

public class PaymentMapper {
    public Payment paymentMapper(ResultSet resultSet) throws Exception {
        return Payment
                .builder()
                .id(resultSet.getInt("id"))
                .payType(PayType.valueOf(resultSet.getString("pay_type")))
                .payDate(resultSet.getDate("pey_date").toLocalDate())
                .price(resultSet.getInt("price"))
                .build();
    }
}
