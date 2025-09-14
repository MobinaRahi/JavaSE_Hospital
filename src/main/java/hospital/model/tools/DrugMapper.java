package hospital.model.tools;
import hospital.model.entity.Drug;
import java.sql.ResultSet;


public class DrugMapper {
    public Drug drugMapper(ResultSet resultSet) throws Exception {
        return Drug
                .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getDouble("price"))
                .build();
    }
}
