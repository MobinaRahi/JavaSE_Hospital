package hospital.model.service;

import hospital.model.entity.Doctor;
import hospital.model.entity.DoctorShift;
import hospital.model.entity.Medical;
import hospital.model.entity.TimeShift;
import hospital.model.repository.TimeShiftRepository;
import hospital.model.tools.ConnectionProvider;
import lombok.Getter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeShiftService implements Service<TimeShift, Integer> {

    @Getter
    private static TimeShiftService service = new TimeShiftService();

    private TimeShiftService() {

    }

    @Override
    public void save(TimeShift timeShift) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            timeShiftRepository.save(timeShift);
        }
    }

    @Override
    public void edit(TimeShift timeShift) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            timeShiftRepository.edit(timeShift);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            timeShiftRepository.delete(id);
        }
    }

    @Override
    public List<TimeShift> findAll() throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            return timeShiftRepository.findAll();
        }
    }

    @Override
    public TimeShift findById(Integer id) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            return timeShiftRepository.findById(id);
        }
    }

    public List<TimeShift> findTimeShiftByDoctorId(int doctorId) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            return timeShiftRepository.findTimeShiftByDoctorId(doctorId);
        }
    }

    public TimeShift findTimeShiftByMedicalId(int medicalId) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            return timeShiftRepository.findTimeShiftByMedicalId(medicalId);
        }
    }

    public void saveDoctorShift(DoctorShift doctorShift, TimeShift timeShift) throws Exception {
        Connection connection = ConnectionProvider.getProvider().getOracleConnection();
        doctorShift.setId(ConnectionProvider.getProvider().getNextId("doctors_shift_seq"));
        PreparedStatement preparedStatement = connection.prepareStatement("insert into doctors_shifts (id,doctor_id,appointment_start,appointment_end) values(?,?,?,?)");
        preparedStatement.setInt(1, doctorShift.getId());
        preparedStatement.setInt(2, timeShift.getDoctor().getId());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(doctorShift.getAppointmentStart()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(doctorShift.getAppointmentEnd()));
        preparedStatement.execute();
    }

    public static List<TimeShift> generateTimeShifts(Doctor doctor, Medical medical, LocalDateTime startDateTime, LocalDateTime endDateTime) throws Exception {
        List<TimeShift> timeShiftList = new ArrayList<>();
        LocalDateTime slotStartDateTime = startDateTime;
        while (!slotStartDateTime.plusMinutes(medical.getDuration()).isAfter(endDateTime)) {
            LocalDateTime slotEndDateTime = slotStartDateTime.plusMinutes(medical.getDuration());
            TimeShift timeShift =
                    TimeShift
                            .builder()
                            .doctor(DoctorService.getService().findById(doctor.getId()))
                            .medical(MedicalService.getService().findById(medical.getId()))
                            .startDateTime(slotStartDateTime)
                            .endDateTime(slotEndDateTime)
                            .build();

            DoctorShift doctorShift=DoctorShift
                    .builder()
                    .doctorId(timeShift.getDoctor().getId())
                    .appointmentStart(timeShift.getStartDateTime())
                    .appointmentEnd(timeShift.getEndDateTime())
                    .build();
            TimeShiftService.getService().saveDoctorShift(doctorShift, timeShift);

            timeShiftList.add(timeShift);
            slotStartDateTime = slotEndDateTime;

        }
        return timeShiftList;
    }


}
