package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Prescription implements Payable {
    private int id;
    private List<Drug> drugList;

    @Override
    public double getPrice() {
        return drugList.stream()
                .mapToDouble(Drug::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }



    }
