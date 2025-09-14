package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Visit implements Payable {
    private int id;
    private double price;
    private Prescription prescription;

    @Override
    public double getPrice() {
        double prescriptionPrice = (prescription != null) ? prescription.getPrice() : 0.0;
        return price + prescriptionPrice;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
