import hospital.model.entity.Doctor;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.DoctorService;
import hospital.model.service.UserService;


import static hospital.model.entity.enums.Specialty.cardiologist;

public class DoctorTest {
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
*/
        Doctor doctor =
                Doctor
                        .builder()
                        .id(1)
                        .user(UserService.getService().findById(1))
                        .specialty(cardiologist)
                        .price(VisitPrice.VISIT2.getPrice())
                        .build();
        //test passed
        //DoctorService.getService().save(doctor);

        //test passed
        //DoctorService.getService().edit(doctor);

        //test passed
        //DoctorService.getService().delete(12);

        //test passed
        //System.out.println(DoctorService.getService().findById(6));

        //test passed
        //System.out.println(DoctorService.getService().findAll());

        //test passed
        //System.out.println(DoctorService.getService().findByUserId(1));

        System.out.println(DoctorService.getService().findBySpecialty("cardiologist"));
    }
}
