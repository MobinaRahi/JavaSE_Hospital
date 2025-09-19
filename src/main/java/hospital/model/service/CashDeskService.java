package hospital.model.service;
import hospital.model.entity.CashDesk;
import hospital.model.repository.CashDeskRepository;

import lombok.Getter;
import java.util.List;

public class CashDeskService implements Service<CashDesk, Integer> {
    @Getter
    private static CashDeskService service = new CashDeskService();

    private CashDeskService(){

    }

    @Override
    public void save (CashDesk cashDesk) throws Exception {
        try (CashDeskRepository cashDeskRepository = new CashDeskRepository()) {
            cashDeskRepository.save(cashDesk);
        }
    }
    @Override
    public void edit (CashDesk cashDesk) throws Exception {
        try (CashDeskRepository cashDeskRepository = new CashDeskRepository()) {
            cashDeskRepository.edit(cashDesk);
        }
    }

    @Override
    public void delete (Integer id) throws Exception {
        try (CashDeskRepository cashDeskRepository = new CashDeskRepository()) {
            cashDeskRepository.delete(id);
        }
    }

    @Override
    public List<CashDesk> findAll ( ) throws Exception {
        try (CashDeskRepository cashDeskRepository = new CashDeskRepository()) {
            return cashDeskRepository.findAll();
        }
    }

    @Override
    public CashDesk findById (Integer id) throws Exception {
        try (CashDeskRepository cashDeskRepository = new CashDeskRepository()) {
            return cashDeskRepository.findById(id);
        }
    }

}
