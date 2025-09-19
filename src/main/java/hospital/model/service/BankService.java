package hospital.model.service;


import hospital.model.entity.Bank;
import hospital.model.entity.Drug;
import hospital.model.repository.BankRepository;
import hospital.model.repository.DrugRepository;
import lombok.Getter;
import java.util.List;

public class BankService implements Service<Bank, Integer> {
    @Getter
    private static BankService service = new BankService();

    private BankService(){

    }

    @Override
    public void save (Bank bank) throws Exception {
        try (BankRepository bankRepository = new BankRepository()) {
            bankRepository.save(bank);
        }
    }

    @Override
    public void edit (Bank bank) throws Exception {
        try (BankRepository bankRepository = new BankRepository()) {
           bankRepository.edit(bank);
        }
    }

    @Override
    public void delete (Integer id) throws Exception {
        try (BankRepository bankRepository = new BankRepository()) {
            bankRepository.delete(id);
        }
    }

    @Override
    public List<Bank> findAll ( ) throws Exception {
        try (BankRepository bankRepository = new BankRepository()) {
            return bankRepository.findAll();
        }
    }

    @Override
    public Bank findById (Integer id) throws Exception {
        try (BankRepository bankRepository = new BankRepository()) {
            return bankRepository.findById(id);
        }
    }



}


