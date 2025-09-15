package hospital.model.entity;

import com.google.gson.Gson;

import java.util.List;

public class Visit {
    private int id;
    //    private Doctor doctor;
//    private Patient patient;
    private List<TimeShift> timeShiftList;
    //    private Prescription prescription;
    private Payment payment;

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
