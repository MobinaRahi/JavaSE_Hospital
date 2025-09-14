package hospital.model.tools;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionProvider {
    // Singleton
    @Getter
    private final static ConnectionProvider provider = new ConnectionProvider();
    private final static BasicDataSource basicDataSource = new BasicDataSource();

    private ConnectionProvider() {
    }

    public Connection getOracleConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        basicDataSource.setUsername("javase");
        basicDataSource.setPassword("java123");
        basicDataSource.setMinIdle(4);
        basicDataSource.setMaxTotal(16);

        return basicDataSource.getConnection();
    }

    public int getNextId(String sequenceName) throws Exception{
        ResultSet resultSet = getOracleConnection().prepareStatement(String.format("select %s.nextval as NEXT_ID from dual", sequenceName)).executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXT_ID");
    }

}
