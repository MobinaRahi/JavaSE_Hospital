package hospital.model.service;

import hospital.model.entity.Doctor;
import hospital.model.entity.Payment;
import hospital.model.repository.PaymentRepository;
import lombok.Getter;
import java.util.List;

public class PaymentService implements Service<Payment,Integer> {
    @Getter
    private static PaymentService service = new PaymentService();

    private PaymentService() {
    }

    @Override
    public void save (Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.save(payment);
        }

    }

    @Override
    public void edit (Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.edit(payment);
        }

    }

    @Override
    public void delete (Integer id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.delete(id);
        }

    }

    @Override
    public List<Payment> findAll ( ) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findAll();
        }
    }

    public List<Payment> findByDoctor ( Doctor doctor ) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findByDoctor(doctor);
        }
    }

    @Override
    public Payment findById (Integer id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findById(id);
        }


    }
}
