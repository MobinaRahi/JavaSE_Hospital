package testServices;

import hospital.model.entity.TimeShift;
import hospital.model.service.DoctorService;
import hospital.model.service.MedicalService;
import hospital.model.service.TimeShiftService;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class TimeShiftTestService {
    public static void saveTimeShifts() throws Exception {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        TimeShift timeShift =
                TimeShift
                        .builder()
                        .doctor(DoctorService.getService().findById(2))
                        .medical(MedicalService.getService().findById(6))
                        .startDateTime(LocalDateTime.of(4000, Month.JANUARY, 1, 11, 30, 0))
                        .endDateTime(LocalDateTime.of(4000, Month.JANUARY, 1, 15, 30, 0))
                        .build();

        TimeShiftService.getService().save(timeShift);
        TimeShift timeShift1 =
                TimeShift
                        .builder()
                        .doctor(DoctorService.getService().findById(4))
                        .medical(MedicalService.getService().findById(37))
                        .startDateTime(LocalDateTime.of(1404, Month.JANUARY, 1, 10, 30, 0))
                        .endDateTime(LocalDateTime.of(1404, Month.JANUARY, 1, 12, 30, 0))
                        .build();

        TimeShiftService.getService().save(timeShift1);

        for (TimeShift generateTimeShift : TimeShiftService.generateTimeShifts(
                DoctorService.getService().findById(timeShift1.getDoctor().getId()),
                MedicalService.getService().findById(timeShift1.getMedical().getId()),
                timeShift1.getStartDateTime(),
                timeShift1.getEndDateTime()))
        {
            System.out.println(generateTimeShift.getStartDateTime().format(timeFormatter) + " -> " + generateTimeShift.getEndDateTime().format(timeFormatter));
        }

    }
}
