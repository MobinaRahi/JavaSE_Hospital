import hospital.model.entity.Bank;
import hospital.model.entity.CashDesk;
import hospital.model.entity.enums.PayType;
import hospital.model.service.BankService;
import hospital.model.service.CashDeskService;
import hospital.model.service.MedicalService;

public class CashDeskTest {
    public static void main(String[] args) throws Exception {
        Bank bank=
                Bank
                        .builder()
                        .title("saderat")
                        .build();

        CashDesk cashDeskCash=
                CashDesk
                        .builder()
                        .bank(BankService.getService().findById(1))
                        .payType(PayType.CASH)
                        .build();
//
//        CashDesk cashDeskCard =
//                CashDesk
//                        .builder()
//                        .bank(bank)
//                        .payType(PayType.CARD)
//                        .build();
//
//        CashDesk cashDeskOnline =
//                CashDesk
//                        .builder()
//                        .bank(bank)
//                        .payType(PayType.ONLINE)
//                        .build();

        CashDeskService.getService().save(cashDeskCash);
//        CashDeskService.getService().edit(cashDeskOnline);
//        CashDeskService.getService().findAll();
//        CashDeskService.getService().findById(658896);
//        CashDeskService.getService().delete(54431684);
    }
}
