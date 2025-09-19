import hospital.model.entity.TimeShift;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;
import hospital.model.service.TimeShiftService;

import java.time.LocalDateTime;
import java.time.Month;

public class TimeShiftTest {
    public static void main(String[] args) throws Exception {

//        Doctor doctor =
//                Doctor
//                        .builder()
//                        .specialty(Specialty.dentist)
//                        .price(VisitPrice.VISIT2.getPrice())
//                        .build();
//
//        DoctorService.getService().save(doctor);

        TimeShift timeShift =
                TimeShift
                        .builder()
//                        .id(2)
                        .doctor(DoctorService.getService().findById(1))
                        .medical(MedicalService.getService().findById(3))
                        .startDateTime(LocalDateTime.of(2004, Month.JANUARY, 1, 11, 30, 0))
                        .endDateTime(LocalDateTime.of(2004, Month.JANUARY, 2, 11, 30, 0))
                        .build();

//        Service test pass

//        test passed
//        TimeShiftService.getService().save(timeShift);

//        test passed
//        TimeShiftService.getService().edit(timeShift);

//        test passed
//        TimeShiftService.getService().delete(4);

//        test passed
//        System.out.println(TimeShiftService.getService().findAll());

//        test passed
//        System.out.println(TimeShiftService.getService().findById(9));

//        test passed
//        System.out.println(TimeShiftService.getService().findTimeShiftByDoctorId(2));

//        test passed
//        System.out.println(TimeShiftService.getService().findTimeShiftByMedicalId(24));


    }
}
