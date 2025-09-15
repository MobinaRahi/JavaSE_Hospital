package hospital.model.tools;

import hospital.model.entity.Doctor;
import hospital.model.entity.enums.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper {

    public Doctor doctorMapper(ResultSet resultSet) throws SQLException {
        return Doctor
                .builder()
                .id(resultSet.getInt("id"))
                .specialty(Specialty.valueOf(resultSet.getString("specialty")))
                .price(resultSet.getInt("price"))
                .build();
    }
}
