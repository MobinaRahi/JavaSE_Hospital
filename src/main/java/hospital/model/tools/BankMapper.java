package hospital.model.tools;
import hospital.model.entity.Bank;
import hospital.model.entity.Drug;

import java.sql.ResultSet;


public class BankMapper {
    public Bank bankMapper(ResultSet resultSet) throws Exception {
        return Bank
                .builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .build();
    }
}