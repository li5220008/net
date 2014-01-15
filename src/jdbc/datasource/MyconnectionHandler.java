package jdbc.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-15
 * Time: 下午5:05
 */
public class MyconnectionHandler implements InvocationHandler {
    private Connection realConnection;
    private Connection proxyConnection;
    private MyDataSource myDataSource;
    private int maxUsedCound =5;//最大重用数
    private int currentUsedCount = 0;//当前重用数
    MyconnectionHandler(MyDataSource myDataSource){
        this.myDataSource = myDataSource;
    }
    public Connection bind(Connection realConnection){
        this.realConnection = realConnection;
        this.proxyConnection = (Connection)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Connection.class},this);
        return this.proxyConnection;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("close".equals(method.getName())){
            this.currentUsedCount++;
            if(this.currentUsedCount<this.maxUsedCound)
                this.myDataSource.connectionPool.addLast(this.proxyConnection);
            else {
                realConnection.close();
                this.myDataSource.currentCount--;
            }
        }
        return method.invoke(this.realConnection,args);
    }
}
