package hospital.model.tools;

import hospital.model.entity.User;
import hospital.model.entity.enums.Role;
import hospital.model.entity.enums.UserName;

import java.sql.ResultSet;

public class UserMapper {

    public User userMapper(ResultSet resultSet) throws Exception {
        return User
                .builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .family(resultSet.getString("family"))
                .birthDate(resultSet.getDate("birth_date").toLocalDate())
                .role(Role.valueOf(resultSet.getString("role")))
                .status(resultSet.getBoolean("status"))
                .username(UserName.valueOf(resultSet.getString("username")))
                .password(resultSet.getString("password"))
                .nickname(resultSet.getString("nick_name"))
                .locked(resultSet.getBoolean("locked"))
                .registerDate(resultSet.getDate("register_date").toLocalDate())
                .build();
    }
}


