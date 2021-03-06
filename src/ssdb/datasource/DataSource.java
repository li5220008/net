package ssdb.datasource;

import com.udpwork.ssdb.SSDB;

import java.util.LinkedList;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-15
 * Time: 上午9:18
 */
public class DataSource {
    private String host = "127.0.0.1";
    private int port = 8888;
    private int timeoutMs = 5000;
    private int minIdle = 10;//最小空闲数
    private int iniCount = 5;//初始化连接
    private int maxCount = 10;//最大连接数

    int currentCount = 0;//当前连接数
    LinkedList<SSDB> ssdbConnectionsPool = new LinkedList<SSDB>();

    public DataSource() {
        try {
            for(int i=0;i<iniCount;i++){
                currentCount++;
                ssdbConnectionsPool.addLast(createSSDB(null));
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public SSDB createSSDB(ThreadLocal<SSDB> cacheSSDB) throws Exception {
        return new MySSDB(host,port,timeoutMs,this,cacheSSDB);
    }

    public SSDB getSSDB(ThreadLocal<SSDB> cacheSSDB) throws Exception{
        synchronized (ssdbConnectionsPool){
            if(ssdbConnectionsPool.size()>0){
                return ssdbConnectionsPool.removeFirst();
            }
            if(currentCount<maxCount){
                currentCount++;
                return createSSDB(cacheSSDB);
            }
            throw new Exception("没有连接了");
        }
    }
}
