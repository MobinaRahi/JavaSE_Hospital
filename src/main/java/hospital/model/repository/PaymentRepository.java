package hospital.model.repository;
import hospital.model.entity.Payment;
import hospital.model.entity.enums.PayType;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.PaymentMapper;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PaymentRepository implements Repository<Payment, Integer>, AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PaymentMapper paymentMapper = new PaymentMapper();

    public PaymentRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getProvider().getNextId("payment_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into Payments (id, pay_type, pay_date_time, price, pay_for, pay_id) values (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setString(2, payment.getPayType().name());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(payment.getPayDateTime()));
        preparedStatement.setFloat(4, payment.getPrice());
        preparedStatement.setString(5, payment.getPayFor().name());
        preparedStatement.setInt(6, payment.getPayable().getId());
//        preparedStatement.setString(7, payment.getPayable().getClass().getSimpleName());
        preparedStatement.execute();
    }

    @Override
    public void edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Payments set pay_type=?, pay_date_time=?, price=?, pay_for=?, pay_id=?, where id=?"
        );
        preparedStatement.setString(1, payment.getPayType().name());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(payment.getPayDateTime()));
        preparedStatement.setFloat(3, payment.getPrice());
        preparedStatement.setString(4, payment.getPayFor().name());
        preparedStatement.setInt(5, payment.getPayable().getId());
        preparedStatement.setString(6, payment.getPayable().getClass().getSimpleName());
        preparedStatement.setInt(7, payment.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("delete from Payments where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Payments");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Payment payment = paymentMapper.paymentMapper(resultSet);
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public Payment findById(Integer id) throws Exception {
        Payment payment = null;
        preparedStatement = connection.prepareStatement("select * from payments where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            payment = paymentMapper.paymentMapper(resultSet);
        }
        return payment;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
