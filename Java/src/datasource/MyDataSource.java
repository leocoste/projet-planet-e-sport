package datasource;

import com.jcraft.jsch.JSchException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MyDataSource {
    /**
     * WARNING : the DataBase needs an SSH tunnel to be accessed
     * run the following command in the client
     * ssh -L 3306:185.187.170.59:3306 sae@host
     * for security reasons,
     */
    Connection getConnection() throws JSchException, SQLException;
    boolean connect();
    boolean disconnect();
}
