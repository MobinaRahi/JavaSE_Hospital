package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class DrugStock {
    private int id;
    private List<Drug> drugList;
    private int drugCount;

    public void addDrug (Drug drug) {
        if (drugList == null) {
            drugList = new ArrayList<>();
        }
        drugList.add(drug);
        drug.setDrugStock(this);
        updateDrugCount();
    }

    private void updateDrugCount ( ) {
        drugCount = 0;
        for (Drug d : drugList) {
            drugCount += d.getQuantity();
        }
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
