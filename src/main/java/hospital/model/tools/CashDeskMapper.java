package hospital.model.tools;

import hospital.model.entity.CashDesk;
import hospital.model.entity.Drug;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CashDeskMapper {
    public CashDesk cashDeskMapper(ResultSet resultSet) throws Exception {
        return CashDesk
                .builder()
                .id(resultSet.getInt("id"))
                .cashBalance(resultSet.getFloat("cash_balance"))
                .bankBalance(resultSet.getFloat("bank_balance"))

                .build();
    }
}

