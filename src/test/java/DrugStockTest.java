import hospital.model.entity.DrugStock;
import hospital.model.service.DrugService;
import hospital.model.service.DrugStockService;

public class DrugStockTest {
    public static void main(String[] args) throws Exception {
        DrugStock drugStock=
                DrugStock
                        .builder()
                        .drug(DrugService.getService().findById(53))
                        .drugCount(100)
                        .build();

        DrugStockService.getService().save(drugStock);
    }
}
