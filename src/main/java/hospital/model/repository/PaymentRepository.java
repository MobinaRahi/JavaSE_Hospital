package hospital.model.repository;


import hospital.model.entity.Doctor;
import hospital.model.entity.Payment;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.PaymentMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements Repository<Payment, Integer>,AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PaymentMapper paymentMapper = new PaymentMapper();

    public PaymentRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save (Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getProvider().getNextId("payment_seq"));
        preparedStatement = connection.prepareStatement(
            "insert into payments (id,pay_type,pay_date,price) values (?,?,?,?)"
    );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setString(2,payment.getPayType().name());
        preparedStatement.setDate(3, Date.valueOf(payment.getPayDate()));
        preparedStatement.setInt(4, payment.getDoctor().getPrice());
        preparedStatement.execute();
    }

    @Override
    public void edit (Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
        "update payments set pay_type=?,pay_date=?,price=? where id=?"
   );
        preparedStatement.setString(1, payment.getPayType().name());
        preparedStatement.setDate(2, Date.valueOf(payment.getPayDate()));
        preparedStatement.setInt(3, payment.getDoctor().getPrice());
        preparedStatement.setInt(4, payment.getId());
        preparedStatement.execute();

    }

    @Override
    public void delete (Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from payments where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Payment> findAll ( ) throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from payments order by pay_type, pay_date");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Payment payment = paymentMapper.paymentMapper(resultSet);
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public Payment findById (Integer id) throws Exception {
        Payment payment = null;
        preparedStatement = connection.prepareStatement("select * from payments where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            payment = paymentMapper.paymentMapper(resultSet);
        }
        return payment;
    }
    public List<Payment> findByDoctor (Doctor doctor) throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from payments where doctor_id=?");
        preparedStatement.setInt(1, doctor.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Payment payment = paymentMapper.paymentMapper(resultSet);
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public void close ( ) throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
