package hospital.model.entity;

import com.google.gson.Gson;
import hospital.model.entity.enums.PayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Payment {
    private int id;
    private PayType payType;
    private Payable payable;

    public double getTotalPrice ( ) {
        if (payable != null) {
            return payable.getPrice();
        }
        return 0.0;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
