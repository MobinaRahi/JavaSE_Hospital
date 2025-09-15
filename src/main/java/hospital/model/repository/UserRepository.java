package hospital.model.repository;


import hospital.model.entity.User;
import hospital.model.tools.ConnectionProvider;
import hospital.model.tools.UserMapper;
import lombok.extern.log4j.Log4j2;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserRepository implements Repository<User, Integer>, AutoCloseable{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final UserMapper userMapper = new UserMapper();

    public UserRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(User user) throws Exception {
        user.setId(ConnectionProvider.getProvider().getNextId("user_seq"));
        preparedStatement = connection.prepareStatement(
                "insert into Users (id,name,family,birth_date,role,status,username,password,nick_name, locked,register_date)  values (?,?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getFamily());
        preparedStatement.setDate(4, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(5, user.getRole().name());
        preparedStatement.setBoolean(6, user.getStatus());
        preparedStatement.setString(7, user.getUsername().name());
        preparedStatement.setString(8, user.getPassword());
        preparedStatement.setString(9, user.getNickname());
        preparedStatement.setBoolean(10, user.getLocked());
        preparedStatement.setDate(11, Date.valueOf(user.getRegisterDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(User user) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Users set name=?, family=?, birth_date=?, role=?, status=?,username=?,password=?,nickName=?, locked=?,register_date=?  where id=?"
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getFamily());
        preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(4, user.getRole().name());
        preparedStatement.setBoolean(5, user.getStatus());
        preparedStatement.setString(6, user.getUsername().name());
        preparedStatement.setString(7, user.getPassword());
        preparedStatement.setString(8, user.getNickname());
        preparedStatement.setBoolean(9, user.getLocked());
        preparedStatement.setDate(10, Date.valueOf(user.getRegisterDate()));
        preparedStatement.setInt(11, user.getId());
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Users where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> userList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = userMapper.userMapper(resultSet);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User findById(Integer id) throws Exception {
        User user = null;
        preparedStatement = connection.prepareStatement("select * from Users where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }

    public User findByUsername(String username) throws Exception {
        User user = null;
        preparedStatement = connection.prepareStatement("select * from Users where username=?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }

    public User findByUsernameAndPassword(String username, String password) throws Exception {
        User user = null;
        preparedStatement = connection.prepareStatement("select * from Users where username=? and password=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}




