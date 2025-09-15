package hospital.model.tools;
import hospital.model.entity.Drug;
import hospital.model.service.DrugStockService;

import java.sql.ResultSet;


public class DrugMapper {
    public Drug drugMapper(ResultSet resultSet) throws Exception {
            return Drug
                    .builder()
                    .id(resultSet.getInt("id"))
                    .drugStock(DrugStockService.getService().findById(resultSet.getInt("stock_id")))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getDouble("price"))
                    .quantity(resultSet.getInt("quantity"))
                    .build();
        }
}

