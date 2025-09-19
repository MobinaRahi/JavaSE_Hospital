package hospital.model.tools;

import hospital.model.entity.CashDesk;
import hospital.model.entity.enums.PayType;
import hospital.model.service.BankService;

import java.sql.ResultSet;


public class CashDeskMapper {
    public CashDesk cashDeskMapper(ResultSet resultSet) throws Exception {
        return CashDesk
                .builder()
                .id(resultSet.getInt("id"))
                .bank(BankService.getService().findById(resultSet.getInt("bank_id")))
                .payType(PayType.valueOf(resultSet.getString("payType")))
                .build();
    }
}

