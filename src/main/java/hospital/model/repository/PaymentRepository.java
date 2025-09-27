package hospital.model.repository;



import hospital.model.entity.Payable;
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
            "insert into payments (id,pay_type,pay_date_time,price,payable) values (?,?,?,?,?)"
    );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setString(2,payment.getPayType().name());
        preparedStatement.setTimestamp(3,Timestamp.valueOf(payment.getPayDateTime()));
        preparedStatement.setFloat(4,payment.getPrice());
        preparedStatement.setInt(5,payment.getPayable().getId());
        preparedStatement.execute();
    }

    @Override
    public void edit (Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
        "update payments set pay_type=?,pay_date_time=?,price=?,payable=? where id=?"
   );
        preparedStatement.setString(1, payment.getPayType().name());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(payment.getPayDateTime()));
        preparedStatement.setFloat(3,payment.getPrice());
        preparedStatement.setObject(4,payment.getPayable());
        preparedStatement.setInt(5, payment.getId());
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
        preparedStatement = connection.prepareStatement("select * from payments order by pay_type, pay_date_time");
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
        public Payment findByPayable (Payable payable) throws Exception {
        Payment payment = null;
        preparedStatement = connection.prepareStatement("select * from payments where payable_id=?"); // فرض بر نام ستون payable_id
        preparedStatement.setInt(1, payable.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            payment = paymentMapper.paymentMapper(resultSet);
        }
        return payment;
    }



    @Override
    public void close ( ) throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
