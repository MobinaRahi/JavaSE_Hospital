package hospital.model.repository;

import hospital.model.entity.Drug;
import hospital.model.entity.Prescription;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.DrugMapper;
import hospital.model.tools.PrescriptionMapper;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PrescriptionRepository implements Repository<Prescription, Integer>, AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    PrescriptionMapper prescriptionMapper = new PrescriptionMapper();

    public PrescriptionRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }


    @Override
    public void save(Prescription prescription) throws Exception {
        prescription.setId(ConnectionProvider.getProvider().getNextId("prescription_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into prescriptions (id,visit_id,price)values (?,?,?)"
        );
        preparedStatement.setInt(1, prescription.getId());
        preparedStatement.setInt(2, prescription.getVisit().getId());
        preparedStatement.setDouble(3, prescription.getPrice());
        preparedStatement.execute();

        for (Drug drug : prescription.getDrugList()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into prescriptions_drugs (prescription_id,drug_id)values (?,?)"
            );
            preparedStatement.setInt(1, prescription.getId());
            preparedStatement.setInt(2, drug.getId());
            preparedStatement.execute();
        }
        log.info("Prescription has been saved successfully");
    }

    @Override
    public void edit(Prescription prescription) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update prescriptions set visit_id=?,price=? where id=?"
        );
        preparedStatement.setInt(1, prescription.getVisit().getId());
        preparedStatement.setDouble(2, prescription.getPrice());
        preparedStatement.setInt(3, prescription.getId());
        preparedStatement.execute();
        log.info("Prescription has been edited successfully");
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from prescriptions where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Prescription has been deleted successfully");
    }

    @Override
    public List<Prescription> findAll() throws Exception {
        List<Prescription> prescriptionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from prescriptions"
        );
        ResultSet prescriptionResultSet = preparedStatement.executeQuery();
        while (prescriptionResultSet.next()) {
            preparedStatement = connection.prepareStatement(
                    "select * from PRESCRIPTIONS_DRUGS where PRESCRIPTION_ID=?"
            );
            preparedStatement.setInt(1, prescriptionResultSet.getInt("ID"));
            ResultSet drugListResultSet = preparedStatement.executeQuery();
            Prescription prescription = prescriptionMapper.prescriptionMapper(prescriptionResultSet, drugListResultSet);
            prescriptionList.add(prescription);
        }
        return prescriptionList;
    }

    @Override
    public Prescription findById(Integer id) throws Exception {
        Prescription prescription = null;
        preparedStatement = connection.prepareStatement(
                "select * from prescriptions where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet prescriptionResultSet = preparedStatement.executeQuery();
        if (prescriptionResultSet.next()) {
            preparedStatement = connection.prepareStatement(
                    "select * from PRESCRIPTIONS_DRUGS where PRESCRIPTION_ID=?"
            );
            preparedStatement.setInt(1, id);
            ResultSet drugListResultSet = preparedStatement.executeQuery();
            prescription = prescriptionMapper.prescriptionMapper(prescriptionResultSet, drugListResultSet);
        }
        return prescription;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

    public List<Drug> showPrescription(Integer prescriptionId) throws Exception {
        DrugMapper drugMapper = new DrugMapper();
        List<Drug> drugList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from drugs join prescriptions_drugs on drugs.id=prescriptions_drugs.drug_id where " +
                        "prescriptions_drugs.prescription_id=?"
        );
        preparedStatement.setInt(1, prescriptionId);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Drug drug = drugMapper.drugMapper(resultSet);
            drugList.add(drug);
        }
        return drugList;
    }

    public List<Drug> updatePrescriptionPrice(Integer prescriptionId) throws Exception {
        DrugMapper drugMapper = new DrugMapper();
        List<Drug> drugList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from drugs join prescriptions_drugs on drugs.id=prescriptions_drugs.drug_id where " +
                        "prescriptions_drugs.prescription_id=?"
        );
        preparedStatement.setInt(1, prescriptionId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Drug drug = drugMapper.drugMapper(resultSet);
            drugList.add(drug);
        }


        double totalPrice = 0;
        for (Drug drug : drugList) {
            totalPrice += drug.getPrice();
        }

        preparedStatement = connection.prepareStatement(
                "update prescriptions set price=? where id=?"
        );
        preparedStatement.setDouble(1, totalPrice);
        preparedStatement.setInt(2, prescriptionId);
        preparedStatement.executeUpdate();

        return drugList;
    }

}
