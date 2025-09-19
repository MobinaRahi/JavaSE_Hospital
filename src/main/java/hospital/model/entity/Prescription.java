package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter

public class Prescription implements Payable {
    private int id;
    private Visit visit;
    private List<Drug> drugList;
    private float price;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

