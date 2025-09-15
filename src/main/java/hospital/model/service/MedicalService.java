package hospital.model.service;

import hospital.model.entity.Medical;
import hospital.model.repository.MedicalRepository;
import lombok.Getter;

import java.util.List;

public class MedicalService implements Service<Medical, Integer> {
    @Getter
    private static MedicalService service = new MedicalService();

    private MedicalService() {

    }

    @Override
    public void save(Medical medical) throws Exception {
        try (MedicalRepository medicalRepository = new MedicalRepository()) {
            medicalRepository.save(medical);
        }
    }

    @Override
    public void edit(Medical medical) throws Exception {
        try (MedicalRepository medicalRepository = new MedicalRepository()) {
            medicalRepository.edit(medical);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (MedicalRepository medicalRepository = new MedicalRepository()) {
            medicalRepository.delete(id);
        }

    }

    @Override
    public List<Medical> findAll() throws Exception {
        try (MedicalRepository medicalRepository = new MedicalRepository()) {
            return medicalRepository.findAll();
        }
    }

    @Override
    public Medical findById(Integer id) throws Exception {
        try (MedicalRepository medicalRepository = new MedicalRepository()) {
            return medicalRepository.findById(id);
        }
    }
}
