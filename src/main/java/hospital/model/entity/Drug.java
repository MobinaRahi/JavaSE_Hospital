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

public class Drug {
    private int id;
    private String name;
    private double price;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

