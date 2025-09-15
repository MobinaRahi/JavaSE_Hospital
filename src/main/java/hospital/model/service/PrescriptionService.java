package hospital.model.service;

import hospital.model.entity.Prescription;

import java.util.Collections;
import java.util.List;

public class PrescriptionService implements Service<Prescription,Integer>{

  private static PrescriptionService service = new PrescriptionService();


    @Override
    public void save(Prescription prescription) throws Exception {

    }

    @Override
    public void edit(Prescription prescription) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<Prescription> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public Prescription findById(Integer id) throws Exception {
        return null;
    }
}
