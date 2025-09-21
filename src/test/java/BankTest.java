import hospital.model.entity.Bank;
import hospital.model.entity.CashDesk;
import hospital.model.entity.enums.PayType;
import hospital.model.service.BankService;
import hospital.model.service.CashDeskService;

public class BankTest {
    public static void main(String[] args) throws Exception{
        Bank bank =
                Bank
                        .builder()
                        .id(2)
                        .title("saderat")
                        .build();




        CashDesk cashDeskCash=
                CashDesk
                        .builder()
                        .bank(BankService.getService().findById(2))
                        .payType(PayType.CASH)
                        .build();
        CashDeskService.getService().save(cashDeskCash);

//        BankService.getService().save(bank);
//        BankService.getService().findById(1);
//        BankService.getService().edit(bank);
//        BankService.getService().findAll();
    }
}
