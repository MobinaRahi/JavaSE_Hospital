package hospital.model.tools;

import hospital.model.entity.Employee;
import hospital.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper {
    public Employee employeeMapper(ResultSet resultSet) throws Exception {
        return Employee
                .builder()
                .id(resultSet.getInt("id"))
                .user(UserService.getService().findById(resultSet.getInt("user_id")))
                .startTime(resultSet.getTime("start_time").toLocalTime())
                .endTime(resultSet.getTime("end_time").toLocalTime())
                .build();
    }
}
