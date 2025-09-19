package hospital.model.tools;

import hospital.model.entity.Doctor;
import hospital.model.entity.enums.Specialty;
import hospital.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper {

    public Doctor doctorMapper(ResultSet resultSet) throws Exception {
        return Doctor
                .builder()
                .id(resultSet.getInt("id"))
                .user(UserService.getService().findById(resultSet.getInt("user_id")))
                .specialty(Specialty.valueOf(resultSet.getString("specialty")))
                .price(resultSet.getInt("price"))
                .build();
    }
}
