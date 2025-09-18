package hospital.model.entity;

import com.google.gson.Gson;
import hospital.model.entity.enums.Specialty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Doctor {
    private int id;
    private Specialty specialty;
    private int price;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
