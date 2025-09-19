package hospital.model.entity;

import com.google.gson.Gson;
import hospital.model.service.DoctorService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.List;


@SuperBuilder
@NoArgsConstructor
@Setter
@Getter

public class Medical {
    private int id;
    private String title;
    private String description;
    private Doctor doctor;
    private int duration;
    private float price;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

