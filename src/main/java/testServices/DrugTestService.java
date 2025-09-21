package testServices;

import hospital.model.entity.Drug;
import hospital.model.service.DrugService;

import java.util.ArrayList;
import java.util.List;

public class DrugTestService {
    public static List<Drug> saveDrugs() throws Exception {

        Drug drug1=
                Drug
                        .builder()
                        .name("aaa2")
                        .price(12500)
                        .quantity(100)
                        .build();
        Drug drug2=
                Drug
                        .builder()
                        .name("bbb2")
                        .price(12500)
                        .quantity(100)
                        .build();
        Drug drug3=
                Drug
                        .builder()
                        .name("ccc2")
                        .price(12500)
                        .quantity(100)
                        .build();
        Drug drug4=
                Drug
                        .builder()
                        .name("ddd2")
                        .price(12500)
                        .quantity(100)
                        .build();

        DrugService.getService().save(drug1);
        DrugService.getService().save(drug2);
        DrugService.getService().save(drug3);
        DrugService.getService().save(drug4);

        List<Drug> drugList=new ArrayList<>();
        drugList.add(drug1);
        drugList.add(drug2);
        drugList.add(drug3);
        drugList.add(drug4);

        return drugList;
    }
}
