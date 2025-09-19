package hospital.model.tools;

import hospital.model.entity.Payable;
import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayFor;
import hospital.model.entity.enums.PayType;
import hospital.model.service.DoctorService;
import hospital.model.service.PatientService;
import hospital.model.service.PrescriptionService;
import hospital.model.service.VisitService;

import java.sql.ResultSet;

public class PaymentMapper {
    public Payment paymentMapper(ResultSet resultSet) throws Exception {
        Payment payment = Payment
                .builder()
                .id(resultSet.getInt("id"))
                .payType(PayType.valueOf(resultSet.getString("pay_type")))
                .payDateTime(resultSet.getTimestamp("pay_date_time").toLocalDateTime())
                .price(resultSet.getFloat("price"))
                .payFor(PayFor.valueOf(resultSet.getString("pay_for")))
                .build();
        int pay_id = resultSet.getInt("pay_id");
        if (payment.getPayFor().equals(PayFor.Visit)) {
            payment.setPayable(VisitService.getService().findById(pay_id));
        }
        if (payment.getPayFor().equals(PayFor.Prescription)) {
            payment.setPayable(PrescriptionService.getService().findById(pay_id));
        }
        return payment;
    }
}
