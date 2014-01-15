package jdbc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Desc: 数据源
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-15
 * Time: 上午11:31
 */
public class MyDataSource0 {
    private static String url = "jdbc:mysql://localhost/jdbc";
    private static String user = "root";
    private static String passwd = "root123";
    private LinkedList<Connection> connectionPool = new LinkedList<Connection>();
    private static int initCount = 5;
    private static int maxCount = 10;
    private static int currentCount = 0;
    public MyDataSource0(){
        try {
            for(int i=0;i< initCount; i++){
                connectionPool.addLast(DriverManager.getConnection(url,user,passwd));
                currentCount ++;
            }
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public Connection getConnection() throws SQLException{
        synchronized (connectionPool){
            if(connectionPool.size()>0) {
                return connectionPool.removeFirst();
            }else if(currentCount < maxCount){
                this.currentCount++;
                return this.createConnection();
            }else {
                throw new SQLException("没有连接了");
            }
        }
    }
    public LinkedList<Connection> getConnectionPool(){
        return this.connectionPool;
    }
    public Connection createConnection() throws SQLException{
        return DriverManager.getConnection(url,user,passwd);
    }
    public void free(Connection conn){
        connectionPool.addLast(conn);
    }
}
