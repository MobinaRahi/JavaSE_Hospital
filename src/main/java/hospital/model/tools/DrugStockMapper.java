package hospital.model.tools;

import hospital.model.entity.DrugStock;
import hospital.model.service.DrugService;

import java.sql.ResultSet;

    public class DrugStockMapper {
    public DrugStock drugStockMapper(ResultSet resultSet) throws Exception {
        return DrugStock
                .builder()
                .id(resultSet.getInt("id"))
                .drug(DrugService.getService().findById(resultSet.getInt("drug_id")))
                .drugCount(resultSet.getInt("drug_count"))
                .build();
    }
}


