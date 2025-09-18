package hospital.model.entity;

import com.google.gson.Gson;
import hospital.model.entity.enums.PayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Payment{
    private int id;
    private PayType payType;
    private LocalDateTime payDateTime;
    private float price;
    private Payable payable;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
