package jdbc;

import java.sql.*;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-14
 * Time: 下午5:51
 */
public class Base {
    public static void main(String[] args)throws Exception{
        //test();
        template();
        testConnection();
    }
    static void template() throws Exception{
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //2建立连接
            conn = JdbcUtils.getConnection();
            //conn = JdbcUtilsSingle.getInstance().getConnection();
            //3创建执行SQL语句(Statement)
            st = conn.createStatement();
            //4执行语句
            rs = st.executeQuery("select * from user");
            //5处理执行结果(ResultSet)
            while (rs.next()){
                System.out.println(String.format("%s %s %s",rs.getObject("name"),rs.getObject("birthday"),rs.getObject("money")));
            }

        } finally {
            //6释放资源
            JdbcUtils.free(rs,st,conn);
            //JdbcUtilsSingle.getInstance().free(rs,st,conn);
        }
    }
    static void testConnection() throws Exception{
        for(int i=0;i<100;i++){
            Connection conn = JdbcUtils.getConnection();
            System.out.println(conn);
            JdbcUtils.free(null,null,conn);
        }
    }
    /*static void template() throws Exception{
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {

            //1注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2建立连接
            String url = "jdbc:mysql://localhost/jdbc";
            String user = "root";
            String passwd = "root123";
            conn = DriverManager.getConnection(url,user,passwd);
            //3创建执行SQL语句(Statement)
            st = conn.createStatement();
            //4执行语句
            rs = st.executeQuery("select * from user");
            //5处理执行结果(ResultSet)
            while (rs.next()){
                System.out.println(String.format("%s %s %s",rs.getObject("name"),rs.getObject("birthday"),rs.getObject("money")));
            }

        } finally {
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
    }*/
    /*static void test() throws Exception{
        //1注册驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
        Class.forName("com.mysql.jdbc.Driver");
        //2建立连接(Connection)
        String url = "jdbc:mysql://localhost/jdbc";
        String user = "root";
        String passwd = "root123";

        Connection conn = DriverManager.getConnection(url,user,passwd);
        //3创建执行SQL语句(Statement)
        Statement st = conn.createStatement();
        //4执行语句
        ResultSet rs = st.executeQuery("select * from user");
        //5处理执行结果(ResultSet)
        while (rs.next()){
            System.out.println(String.format("%s %s %s",rs.getObject(1),rs.getObject(2),rs.getObject(3)));
        }
        //6释放资源
        rs.close();
        st.close();
        conn.close();
    }*/
}
