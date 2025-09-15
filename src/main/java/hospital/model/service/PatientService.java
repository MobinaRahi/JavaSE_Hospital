package hospital.model.service;

import hospital.model.entity.Patient;
import hospital.model.repository.PatientRepository;
import lombok.Getter;

import java.util.List;

public class PatientService implements Service<Patient , Integer> {

    @Getter
    public static PatientService service = new PatientService();

    private PatientService() {
    }

    @Override
    public void save(Patient patient) throws Exception {
        try (PatientRepository patientRepository = new PatientRepository()) {
            patientRepository.save(patient);
        }
    }

    @Override
    public void edit(Patient patient) throws Exception {
        try (PatientRepository patientRepository = new PatientRepository()) {
            patientRepository.save(patient);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (PatientRepository patientRepository = new PatientRepository()) {
            patientRepository.delete(id);
        }
    }

    @Override
    public List<Patient> findAll() throws Exception {
        try (PatientRepository patientRepository = new PatientRepository()) {
            return patientRepository.findAll();
        }
    }

    @Override
    public Patient findById(Integer id) throws Exception {
        try (PatientRepository patientRepository = new PatientRepository()) {
            return patientRepository.findById(id);
        }
    }
}
