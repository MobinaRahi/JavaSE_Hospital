package hospital.model.service;

import hospital.model.entity.Visit;
import hospital.model.repository.VisitRepository;
import lombok.Getter;

import java.util.List;

public class VisitService implements Service<Visit,Integer>{

    @Getter
    private static VisitService service = new VisitService();

    private VisitService(){

    }

    @Override
    public void save(Visit visit) throws Exception {
        try (VisitRepository visitRepository = new VisitRepository()) {
            visitRepository.save(visit);
        }
    }

    @Override
    public void edit(Visit visit) throws Exception {
        try (VisitRepository visitRepository = new VisitRepository()) {
            visitRepository.save(visit);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (VisitRepository visitRepository = new VisitRepository()) {
            visitRepository.delete(id);
        }
    }

    @Override
    public List<Visit> findAll() throws Exception {
        try (VisitRepository visitRepository = new VisitRepository()) {
            return visitRepository.findAll();
        }
    }

    @Override
    public Visit findById(Integer id) throws Exception {
        try (VisitRepository visitRepository = new VisitRepository()) {
            return visitRepository.findById(id);
        }
    }
}
