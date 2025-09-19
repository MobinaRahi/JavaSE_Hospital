import hospital.model.entity.Employee;
import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.service.EmployeeService;
import hospital.model.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeeTest {
    public static void main(String[] args) throws Exception {

       /* User user =
                User
                        .builder()
                        .name("sara")
                        .family("moradi")
                        .birthDate(LocalDate.of(2000, 1, 1))
                        .role(Role.DOCTOR)
                        .status(true).username("sara")
                        .password("sara123")
                        .nickname("sar sar")
                        .locked(true)
                        .registerDate(LocalDate.of(2025, 8, 25))
                        .build();

        UserService.getService().save(user);
*/
        Employee employee =
                Employee
                        .builder()
                        .user(UserService.getService().findById(1))
                        .startTime(LocalTime.of(7,28))
                        .endTime(LocalTime.of(11,30))
                        .build();


        //test passed
        //EmployeeService.getService().save(employee);

        //test passed
        //EmployeeService.getService().edit(employee);

        //test passed
        //EmployeeService.getService().delete(5);

        //test passed
        //System.out.println(EmployeeService.getService().findById(6));

        //test passed
        //System.out.println(EmployeeService.getService().findByUserId(1));
    }

}
