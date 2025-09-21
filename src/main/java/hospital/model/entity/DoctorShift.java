package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class DoctorShift {
    private int id;
    private int doctorId;
    private int status;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
