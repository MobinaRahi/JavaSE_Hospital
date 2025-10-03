package hospital.model.service;

import hospital.model.entity.Drug;
import hospital.model.entity.Prescription;
import hospital.model.repository.PrescriptionRepository;
import lombok.Getter;
import java.util.List;

public class PrescriptionService implements Service<Prescription, Integer> {

    @Getter
    private static PrescriptionService service = new PrescriptionService();

    private PrescriptionService() {

    }

    @Override
    public void save(Prescription prescription) throws Exception {
        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            prescriptionRepository.save(prescription);
        }
    }

    @Override
    public void edit(Prescription prescription) throws Exception {

        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            prescriptionRepository.edit(prescription);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {

        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            prescriptionRepository.delete(id);
        }

    }

    @Override
    public List<Prescription> findAll() throws Exception {

        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            return prescriptionRepository.findAll();
        }

    }

    @Override
    public Prescription findById(Integer id) throws Exception {

        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            return prescriptionRepository.findById(id);
        }

    }

    public void removeDrugFromPrescription(int prescriptionId, int drugId) throws Exception{
        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            prescriptionRepository.removeDrugFromPrescription(prescriptionId,drugId);
        }
    }
    public void addDrugToPrescription(int prescriptionId, int drugId) throws Exception{
        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            prescriptionRepository.addDrugToPrescription(prescriptionId,drugId);
        }
    }

public List<Drug> showPrescription(Integer prescriptionId) throws Exception {
        try (PrescriptionRepository prescriptionRepository = new PrescriptionRepository()) {
            return prescriptionRepository.showPrescription(prescriptionId);
        }
    }

}


