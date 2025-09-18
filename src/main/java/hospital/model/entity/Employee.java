package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Employee {
    private User user;
    private String memberName;
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
