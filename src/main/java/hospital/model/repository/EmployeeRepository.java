package hospital.model.repository;

import hospital.model.entity.Employee;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.EmployeeMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee, Integer>, AutoCloseable{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final EmployeeMapper employeeMapper = new EmployeeMapper();


    public EmployeeRepository() throws SQLException{
        this.connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Employee employee) throws Exception {
        employee.setId(ConnectionProvider.getProvider().getNextId("employee_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into employees (id,user_id,start_time,end_time) values(?,?,?,?)"
        );
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.setInt(2, employee.getUser().getId());
        preparedStatement.setTime(3, Time.valueOf(employee.getStartTime()));
        preparedStatement.setTime(4,Time.valueOf(employee.getEndTime()));
        preparedStatement.execute();
    }

    @Override
    public void edit(Employee employee) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update employees set start_time=?,end_time=? where id=?"
        );
        preparedStatement.setTime(1, Time.valueOf(employee.getStartTime()));
        preparedStatement.setTime(2,Time.valueOf(employee.getEndTime()));
        preparedStatement.setInt(3, employee.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from employees where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Employee> findAll() throws Exception {
        List<Employee> employees = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from employees"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Employee employee = employeeMapper.employeeMapper(resultSet);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) throws Exception {
        Employee employee = null;
        preparedStatement =connection.prepareStatement(
                "select * from employees where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            employee = employeeMapper.employeeMapper(resultSet);
        }
        return employee;
    }

    public List<Employee> findByUserId(Integer userId) throws Exception {
        List<Employee> employees = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "select * from employees where user_id=?"
        );
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Employee employee = employeeMapper.employeeMapper(resultSet);
            employees.add(employee);
        }
        return employees;
    }




    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

