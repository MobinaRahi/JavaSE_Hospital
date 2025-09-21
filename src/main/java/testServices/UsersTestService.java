package testServices;

import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.service.UserService;

import java.time.LocalDate;
import java.time.Month;

public class UsersTestService {
    public static void saveUsers() throws Exception {
        User user1 = User
                .builder()
                .name("test")
                .family("test")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
                .role(Role.DOCTOR)
                .status(true)
                .username("test1")
                .password("test")
                .nickname("test")
                .locked(true)
                .registerDate(LocalDate.now())
                .build();

        User user2 = User
                .builder()
                .name("test")
                .family("test")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
                .role(Role.PATIENT)
                .status(true)
                .username("test2")
                .password("test")
                .nickname("test")
                .locked(true)
                .registerDate(LocalDate.now())
                .build();

        User user3 = User
                .builder()
                .name("test")
                .family("test")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
                .role(Role.EMPLOYEE)
                .status(true)
                .username("test3")
                .password("test")
                .nickname("test")
                .locked(true)
                .registerDate(LocalDate.now())
                .build();

        User user4 = User
                .builder()
                .name("test")
                .family("test")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
                .role(Role.DOCTOR)
                .status(true)
                .username("test4")
                .password("test")
                .nickname("test")
                .locked(true)
                .registerDate(LocalDate.now())
                .build();

        UserService.getService().save(user1);
        UserService.getService().save(user2);
        UserService.getService().save(user3);
        UserService.getService().save(user4);
    }

    public static User login(String username, String password) throws Exception {
        return UserService.getService().findByUsernameAndPassword(username,password);
    }
}
