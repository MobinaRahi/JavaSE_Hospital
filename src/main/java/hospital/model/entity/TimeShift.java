package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter

public class TimeShift {
    private int id;
    //    private Doctor doctor;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
