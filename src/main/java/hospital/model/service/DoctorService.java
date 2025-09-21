package hospital.model.service;

import hospital.model.entity.Doctor;
import hospital.model.entity.DoctorShift;
import hospital.model.entity.Patient;
import hospital.model.entity.TimeShift;
import hospital.model.repository.DoctorRepository;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class DoctorService implements Service<Doctor, Integer> {

    @Getter
    public static DoctorService service = new DoctorService();

    private DoctorService() {
    }

    @Override
    public void save(Doctor doctor) throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            doctorRepository.save(doctor);
            log.info("Doctor Saved");
        }
    }

    @Override
    public void edit(Doctor doctor) throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            doctorRepository.edit(doctor);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            doctorRepository.delete(id);
        }
    }

    @Override
    public List<Doctor> findAll() throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            return doctorRepository.findAll();
        }
    }

    @Override
    public Doctor findById(Integer id) throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            return doctorRepository.findById(id);
        }
    }

    public List<Doctor> findByUserId(Integer userId) throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            return doctorRepository.findByUserId(userId);
        }
    }

    public List<Doctor> findBySpecialty(String specialty) throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            return doctorRepository.findBySpecialty(specialty);
        }
    }

    public List<Doctor> findByNameAndFamily(String name, String family) throws Exception {
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            return doctorRepository.findByNameAndFamily(name,family);
        }
    }

    public List<DoctorShift> findBookedTimeShifts() throws Exception{
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            return doctorRepository.findBookedTimeShifts();
        }
    }

    public Patient showPatientInformation(Integer visitId) throws Exception{
        try (DoctorRepository doctorRepository = new DoctorRepository()) {
            return doctorRepository.showPatientInformation(visitId);
        }
    }
}
