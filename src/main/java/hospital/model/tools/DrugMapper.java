package hospital.model.tools;
import hospital.model.entity.Drug;
import hospital.model.service.DrugStockService;

import java.sql.ResultSet;


public class DrugMapper {
    public Drug drugMapper(ResultSet resultSet) throws Exception {
            return Drug
                    .builder()
                    .id(resultSet.getInt("DRUG_ID"))
                    .drugStock(DrugStockService.getService().findById(resultSet.getInt("STOCK_ID")))
                    .name(resultSet.getString("NAME"))
                    .price(resultSet.getDouble("PRICE"))
                    .quantity(resultSet.getInt("QUANTITY"))
                    .build();
        }
}

