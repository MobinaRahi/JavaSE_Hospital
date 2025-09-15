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

public class Prescription {
    private int id;
    private Patient patient;
    private Visit visit;
    private Payment payment;
    private List<Drug> drugList;
    private Employee employee;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

