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

public class DrugStock {
    private List<Drug> drugList;
    private int drugCount;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
