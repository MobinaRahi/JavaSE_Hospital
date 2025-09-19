import hospital.model.entity.Bank;
import hospital.model.service.BankService;

public class BankTest {
    public static void main(String[] args) throws Exception{
        Bank bank =
                Bank
                        .builder()
                        .id(812312)
                        .title("saderat")
                        .build();

        BankService.getService().save(bank);
        BankService.getService().findById(812312);
        BankService.getService().edit(bank);
        BankService.getService().findAll();
    }
}
