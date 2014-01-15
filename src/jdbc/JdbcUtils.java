package jdbc;

import jdbc.datasource.MyDataSource;

import java.sql.*;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-15
 * Time: 上午10:22
 */
public final class JdbcUtils {
    private static MyDataSource myDataSource = null;
    private JdbcUtils(){};
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myDataSource = new MyDataSource();
            //myDataSource = new MyDataSource0();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static Connection getConnection() throws Exception{
        return myDataSource.getConnection();
    }

    public static void free(ResultSet rs, Statement st, Connection conn){
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
                        //myDataSource.free(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
