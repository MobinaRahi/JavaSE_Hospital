package hospital.model.service;

import hospital.model.entity.Drug;
import hospital.model.entity.Prescription;
import hospital.model.repository.PrescriptionRepository;
import hospital.model.tools.ConnectionProvider;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public static void prescriptionsDrugs(List<Drug>drugList,int prescription_id) throws SQLException {
        
        for (Drug drug : drugList) {
            Connection connection = ConnectionProvider.getProvider().getOracleConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into prescriptions_drugs (prescription_id,drug_id)values (?,?)"
            );
            preparedStatement.setInt(1, prescription_id);
            preparedStatement.setInt(2,drug.getId());
            preparedStatement.execute();
        }
    }

}


