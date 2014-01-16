package ssdb.datasource;

import com.udpwork.ssdb.SSDB;

import java.util.LinkedList;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-16
 * Time: 上午9:18
 */
public class DataSource {
    private String host = "192.168.98.250";
    private int port = 9966;
    private int timeoutMs = 5000;
    private int iniCount = 5;//初始化连接
    private int maxCount = 10;//最大连接数
    int currentCount = 0;//当前连接数
    LinkedList<SSDB> ssdbConnectionsPool = new LinkedList<SSDB>();

    public DataSource() {
        try {
            for(int i=0;i<iniCount;i++){
                currentCount++;
                ssdbConnectionsPool.addLast(createSSDB());
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public SSDB createSSDB() throws Exception {
        return new MySSDB(host,port,timeoutMs,this);
    }

    public SSDB getSSDB() throws Exception{
        synchronized (ssdbConnectionsPool){
            if(ssdbConnectionsPool.size()>0){
                return ssdbConnectionsPool.removeFirst();
            }
            if(currentCount<maxCount){
                currentCount++;
                return createSSDB();
            }
            throw new Exception("没有连接了");
        }
    }
}
