package testServices;

import hospital.model.entity.Employee;
import hospital.model.service.EmployeeService;
import hospital.model.service.UserService;

import java.time.LocalTime;

public class EmployeeTestService {
    public static void saveEmployees() throws Exception {

        Employee employee =
                Employee
                        .builder()
                        .user(UserService.getService().findById(1))
                        .startTime(LocalTime.of(7,28))
                        .endTime(LocalTime.of(11,30))
                        .build();


//          test passed
        EmployeeService.getService().save(employee);
    }
}
