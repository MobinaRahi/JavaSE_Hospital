package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter

public class Visit implements Payable{
    private int id;
    private Doctor doctor;
    private Patient patient;
    private TimeShift timeShift;
    private Payment payment;
    private double price;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
