package hospital.model.service;
import hospital.model.entity.DrugStock;
import hospital.model.repository.DrugStockRepository;
import lombok.Getter;
import java.util.List;

public class DrugStockService implements Service<DrugStock,Integer> {
    @Getter
    private static DrugStockService service = new DrugStockService();

    private DrugStockService(){

    }


    @Override
    public void save (DrugStock drugStock) throws Exception {
        try (DrugStockRepository drugStockRepository = new DrugStockRepository()) {
            drugStockRepository.save(drugStock);
        }
    }

    @Override
    public void edit (DrugStock drugStock) throws Exception {
        try (DrugStockRepository drugStockRepository = new DrugStockRepository()) {
            drugStockRepository.edit(drugStock);
        }
    }

    @Override
    public void delete (Integer id) throws Exception {
        try (DrugStockRepository drugStockRepository = new DrugStockRepository()) {
            drugStockRepository.delete(id);
        }
    }

    @Override
    public List<DrugStock> findAll ( ) throws Exception {
        try (DrugStockRepository drugStockRepository = new DrugStockRepository()) {
            return drugStockRepository.findAll();
        }
    }

    @Override
    public DrugStock findById (Integer id) throws Exception {
        try (DrugStockRepository drugStockRepository = new DrugStockRepository()) {
            return drugStockRepository.findById(id);
        }
    }
}
