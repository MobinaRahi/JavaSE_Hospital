package hospital.model.tools;

import hospital.model.entity.CashDesk;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashDeskMapper {
    public static CashDesk map(ResultSet rs) throws SQLException {
        if (rs == null) return null;

        return CashDesk.builder()
                .id(rs.getInt("id"))
                .cashBalance(rs.getFloat("cash_balance"))
                .bankBalance(rs.getFloat("bank_balance"))
                .build();
    }
}
