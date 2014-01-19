package jdbc.datasource;

import javax.sql.DataSource;
import java.io.PrintWriter;
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
public class MyDataSource1 {
    private static String url = "jdbc:mysql://localhost/jdbc";
    private static String user = "root";
    private static String passwd = "root123";
    LinkedList<Connection> connectionPool = new LinkedList<Connection>();
    private int initCount = 1;//初始化连接数
    private int maxCount = 1;//最大连接数
    int currentCount = 0;//当前连接数
    public MyDataSource1(){
        try {
            for(int i=0;i< initCount; i++){
                connectionPool.addLast(this.createConnection());
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

    /*public int getCurrentCount(){
        return this.currentCount;
    }
    public LinkedList<Connection> getConnectionPool(){
        return this.connectionPool;
    }*/
    public Connection createConnection() throws SQLException{
        Connection realConnection = DriverManager.getConnection(url,user,passwd);
        //MyConnection myConnection = new MyConnection(realConnection,this);
        //Connection proxyConnection = new MyconnectionHandler(this).bind(realConnection);
        return realConnection;
    }
    public void free(Connection conn){
        connectionPool.addLast(conn);
    }
}
