package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter

public class Visit {
    private int id;
    //    private Doctor doctor;
//    private Patient patient;
    private List<TimeShift> timeShiftList;
    private Payment payment;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
