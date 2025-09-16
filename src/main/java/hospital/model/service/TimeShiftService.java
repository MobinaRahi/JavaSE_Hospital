package hospital.model.service;

import hospital.model.entity.TimeShift;
import hospital.model.repository.TimeShiftRepository;
import lombok.Getter;


import java.util.List;

public class TimeShiftService implements Service<TimeShift, Integer> {

    @Getter
    private static TimeShiftService service = new TimeShiftService();

    private TimeShiftService() {

    }

    @Override
    public void save(TimeShift timeShift) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            timeShiftRepository.save(timeShift);
        }
    }

    @Override
    public void edit(TimeShift timeShift) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            timeShiftRepository.edit(timeShift);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            timeShiftRepository.delete(id);
        }
    }

    @Override
    public List<TimeShift> findAll() throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            return timeShiftRepository.findAll();
        }
    }

    @Override
    public TimeShift findById(Integer id) throws Exception {
        try (TimeShiftRepository timeShiftRepository = new TimeShiftRepository()) {
            return timeShiftRepository.findById(id);
        }
    }
}
