package jdbc;

import java.sql.*;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-15
 * Time: 上午10:50
 */
public class JdbcUtilsSingle {
    private String url = "jdbc:mysql://localhost/jdbc";
    private String user = "root";
    private String passwd = "root123";
    private static JdbcUtilsSingle instance = null;
    private JdbcUtilsSingle(){}

    public static JdbcUtilsSingle getInstance() {
        if(instance == null){
            synchronized (JdbcUtilsSingle.class){
                if(instance == null)
                instance = new JdbcUtilsSingle();
            }
        }
        return instance;
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void free(ResultSet rs, Statement st, Connection conn){
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(st != null)
                    st.close();
            } catch (SQLException e){e.printStackTrace();}
            finally {
                try {
                    if(conn != null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
