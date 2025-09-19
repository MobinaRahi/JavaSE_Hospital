package hospital.model.tools;

import hospital.model.entity.Doctor;
import hospital.model.entity.Patient;
import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.entity.enums.Specialty;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.DoctorService;
import hospital.model.service.PatientService;
import hospital.model.service.UserService;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) throws Exception {

        //create user for SignUp

//        User user1 = User
//                .builder()
//                .name("test")
//                .family("test")
//                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
//                .role(Role.DOCTOR)
//                .status(true)
//                .username("test1")
//                .password("test")
//                .nickname("test")
//                .locked(true)
//                .registerDate(LocalDate.now())
//                .build();

//        User user2 = User
//                .builder()
//                .name("test")
//                .family("test")
//                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
//                .role(Role.PATIENT)
//                .status(true)
//                .username("test2")
//                .password("test")
//                .nickname("test")
//                .locked(true)
//                .registerDate(LocalDate.now())
//                .build();

//        User user3 = User
//                .builder()
//                .name("test")
//                .family("test")
//                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
//                .role(Role.EMPLOYEE)
//                .status(true)
//                .username("test3")
//                .password("test")
//                .nickname("test")
//                .locked(true)
//                .registerDate(LocalDate.now())
//                .build();

//        User user4 = User
//                .builder()
//                .name("test")
//                .family("test")
//                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
//                .role(Role.DOCTOR)
//                .status(true)
//                .username("test4")
//                .password("test")
//                .nickname("test")
//                .locked(true)
//                .registerDate(LocalDate.now())
//                .build();
//
//        UserService.getService().save(user1);
//        UserService.getService().save(user2);
//        UserService.getService().save(user3);
//        UserService.getService().save(user4);

//        Doctor doctor=
//                Doctor
//                        .builder()
//                        .user(UserService.getService().findById(1))
//                        .specialty(Specialty.dentist)
//                        .price(VisitPrice.VISIT1.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor);
//
//        Doctor doctor1=
//                Doctor
//                        .builder()
//                        .user(UserService.getService().findById(1))
//                        .specialty(Specialty.dentist)
//                        .price(VisitPrice.VISIT1.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor1);

//        Doctor doctor2=
//                Doctor
//                        .builder()
//                        .user(UserService.getService().findById(1))
//                        .specialty(Specialty.dermatologist)
//                        .price(VisitPrice.VISIT1.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor2);


        //test signIn

//        String userName = "mmm";
//        String password = "mmm";
//        for (User user : UserService.getService().findAll()) {
//            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {

//            }
//        }

        //about patient

//        Patient patient =
//                Patient
//                        .builder()
//                        .user(UserService.getService().findById(3))
//                        .build();
//
//        PatientService.getService().save(patient);

//        DoctorService.getService().findAll().forEach(System.out::println);

//        String name = "test";
//        String family = "test";
//        System.out.println(DoctorService.getService().findByNameAndFamily(name, family));
//        System.out.println(DoctorService.getService().findBySpecialty("dermatologist"));




    }
}
