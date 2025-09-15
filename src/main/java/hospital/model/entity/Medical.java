package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@Setter
@Getter

public class Medical {
    private int id;
    private String title;
    private String description;
//    private List<Doctor> doctorList;
    private float duration;
    private int payment;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

