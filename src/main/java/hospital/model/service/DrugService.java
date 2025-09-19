package hospital.model.service;

import hospital.model.entity.Drug;
import hospital.model.repository.DrugRepository;
import lombok.Getter;
import java.util.List;

public class DrugService implements Service<Drug, Integer> {
    @Getter
    private static DrugService service = new DrugService();

    private DrugService(){

    }

    @Override
    public void save (Drug drug) throws Exception {
        try (DrugRepository drugRepository = new DrugRepository()) {
            drugRepository.save(drug);
        }
    }

    @Override
    public void edit (Drug drug) throws Exception {
        try (DrugRepository drugRepository = new DrugRepository()) {
            drugRepository.edit(drug);
        }
    }

    @Override
    public void delete (Integer id) throws Exception {
        try (DrugRepository drugRepository = new DrugRepository()) {
            drugRepository.delete(id);
        }
    }

    @Override
    public List<Drug> findAll ( ) throws Exception {
        try (DrugRepository drugRepository = new DrugRepository()) {
            return drugRepository.findAll();
        }
    }

    @Override
    public Drug findById (Integer id) throws Exception {
        try (DrugRepository drugRepository = new DrugRepository()) {
            return drugRepository.findById(id);
        }
    }

    public List<Drug> findByName(String name) throws Exception {
        try (DrugRepository drugRepository = new DrugRepository()) {
            return drugRepository.findByName(name);
        }
    }


}
