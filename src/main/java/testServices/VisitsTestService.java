package testServices;

import hospital.model.entity.User;
import hospital.model.entity.Visit;
import hospital.model.entity.enums.Role;
import hospital.model.entity.enums.VisitPrice;
import hospital.model.service.*;

import java.time.LocalDate;
import java.time.Month;

public class VisitsTestService {
    public static void saveVisits() throws Exception {
        Visit visit =
                Visit
                        .builder()
                        .doctor(DoctorService.getService().findById(1))
                        .patient(PatientService.getService().findById(3))
                        .timeShift(TimeShiftService.getService().findById(2))
                        .price(VisitPrice.VISIT1.getPrice())
                        .build();

        VisitService.getService().save(visit);

    }
}
