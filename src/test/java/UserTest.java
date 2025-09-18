import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.service.UserService;

import java.time.LocalDate;

public class UserTest {
    public static void main(String[] args) throws Exception {
        User user =
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

        //test passed
        //UserService.getService().save(user);

        //test passed
        //UserService.getService().edit(user);

        //test passed
        //UserService.getService().delete(1);

        //test passed
        //System.out.println(UserService.getService().findAll());

        //test error
        //System.out.println(UserService.getService().findById(1));
    }
}
