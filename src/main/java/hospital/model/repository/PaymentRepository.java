package hospital.model.repository;



import hospital.model.entity.Payable;
import hospital.model.entity.Payment;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.PaymentMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements Repository<Payment, Integer>, AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private PaymentMapper paymentMapper;

    public PaymentRepository() throws SQLException {
        this.connection = ConnectionProvider.getConnection();
        this.paymentMapper = new PaymentMapper();
    }

    @Override
    public void save(Payment payment) throws Exception {
        try (PreparedStatement idStatement = connection.prepareStatement("SELECT payment_seq.NEXTVAL FROM dual")) {
            ResultSet rs = idStatement.executeQuery();
            if (rs.next()) {
                payment.setId(rs.getInt(1));
            }
        }

        preparedStatement = connection.prepareStatement(
                "INSERT INTO payments (id, pay_type, pay_date_time, price, payable_id) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setString(2, payment.getPayType().name());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(payment.getPayDateTime()));
        preparedStatement.setFloat(4, payment.getPrice());
        preparedStatement.setInt(5, payment.getPayable().getId());
        preparedStatement.execute();
    }

    @Override
    public void edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE payments SET pay_type=?, pay_date_time=?, price=?, payable_id=? WHERE id=?"
        );
        preparedStatement.setString(1, payment.getPayType().name());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(payment.getPayDateTime()));
        preparedStatement.setFloat(3, payment.getPrice());
        preparedStatement.setInt(4, payment.getPayable().getId());
        preparedStatement.setInt(5, payment.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM payments WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> payments = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM payments ORDER BY pay_type, pay_date_time");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            payments.add(paymentMapper.map(resultSet));
        }
        return payments;
    }

    @Override
    public Payment findById(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM payments WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return paymentMapper.map(resultSet);
        }
        return null;
    }

    public Payment findByPayable(Payable payable) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM payments WHERE payable_id=?");
        preparedStatement.setInt(1, payable.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return paymentMapper.map(resultSet);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    static class Payment {
        private int id;
        private PaymentType payType;
        private java.time.LocalDateTime payDateTime;
        private float price;
        private Payable payable;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public PaymentType getPayType() { return payType; }
        public java.time.LocalDateTime getPayDateTime() { return payDateTime; }
        public float getPrice() { return price; }
        public Payable getPayable() { return payable; }
    }

    static class Payable {
        private int id;
        public int getId() { return id; }
    }

    enum PaymentType {
        CARD, CASH, TRANSFER
    }

    static class ConnectionProvider {
        public static Connection getConnection() throws SQLException {
             return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        }
    }

    static class PaymentMapper {
        public Payment map(ResultSet resultSet) throws SQLException {
            Payment payment = new Payment();
            payment.setId(resultSet.getInt("id"));
            payment.payType = PaymentType.valueOf(resultSet.getString("pay_type"));
            payment.payDateTime = resultSet.getTimestamp("pay_date_time").toLocalDateTime();
            payment.price = resultSet.getFloat("price");

            Payable payable = new Payable();
            payable.id = resultSet.getInt("payable_id");
            payment.payable = payable;

            return payment;
        }
    }

    public static void main(String[] args) {
        try {
            PaymentRepository paymentRepository = new PaymentRepository();

            Payment newPayment = new Payment();
            newPayment.payType = PaymentType.CARD;
            newPayment.payDateTime = java.time.LocalDateTime.now();
            newPayment.price = 100.50f;
            Payable associatedPayable = new Payable();
            associatedPayable.id = 1;
            newPayment.payable = associatedPayable;

            paymentRepository.save(newPayment);
            System.out.println("Payment saved with ID: " + newPayment.getId());

            Payment foundPayment = paymentRepository.findById(newPayment.getId());
            if (foundPayment != null) {
                System.out.println("Found payment by ID: " + foundPayment.getId() + " - Price: " + foundPayment.getPrice());
            }

            foundPayment.setPrice(120.75f);
            paymentRepository.edit(foundPayment);
            Payment updatedPayment = paymentRepository.findById(foundPayment.getId());
            System.out.println("Updated payment price: " + updatedPayment.getPrice());

             Payable payableToFind = new Payable();
             payableToFind.id = 1;
             Payment foundByPayable = paymentRepository.findByPayable(payableToFind);
             if (foundByPayable != null) {
                 System.out.println("Found payment by Payable ID: " + foundByPayable.getId() + " - Payable ID: " + foundByPayable.getPayable().getId());
             } else {
                 System.out.println("No payment found for Payable ID: " + payableToFind.getId());
             }

            List<Payment> allPayments = paymentRepository.findAll();
            System.out.println("Total payments found: " + allPayments.size());

            paymentRepository.delete(newPayment.getId());
            Payment deletedPayment = paymentRepository.findById(newPayment.getId());
            if (deletedPayment == null) {
                System.out.println("Payment successfully deleted.");
            }

            paymentRepository.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
