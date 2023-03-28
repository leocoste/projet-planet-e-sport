package datasource.mysql;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import datasource.MyDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.sql.DriverManager;

public class MySQLDataSource implements MyDataSource  {
    private static MySQLDataSource instance;
    private Connection connection;
    private static final String user = "sae";
    private static final String pwd = "untrucrandom";
    private static final String sshHost = "185.187.170.59";
    private static final String url = "jdbc:mysql://127.0.0.1:";
    private static final int port = 3306;

    private MySQLDataSource(){
        connect();
    }
    public static MySQLDataSource getInstance() {
        if(instance == null) instance = new MySQLDataSource();
        return instance;
    }
    public boolean connect(){
        try{
            if(this.connection == null) this.connection = DriverManager.getConnection(url+port, user, pwd);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    @Override
    public boolean disconnect() {
        try {
            if (this.connection != null) {
                this.connection.close();
                this.connection = null;
                return true;
            }
        }catch(SQLException e){
            return false;
        }
        return false;
    }
    @Override
    public Connection getConnection(){
        this.connect();
        return connection;
    }
}
