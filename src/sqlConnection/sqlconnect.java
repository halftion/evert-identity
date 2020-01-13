package sqlConnection;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class sqlconnect {
    private static sqlconnect pool = null;
    private static DataSource datasource = null;

    private sqlconnect(){
        try {
            InitialContext initcon = new InitialContext();
            datasource = (DataSource)initcon.lookup("java:/comp/env/jdbc/cmssql");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized sqlconnect getInstance(){
        if(pool == null) pool = new sqlconnect();
        return pool;
    }

    public Connection getConnection(){
        try {
            return datasource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void freeConnection(Connection c){
        try {
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
