package hospital.model.service;

import hospital.model.entity.Doctor;
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
}
