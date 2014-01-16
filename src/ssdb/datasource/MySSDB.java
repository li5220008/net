package ssdb.datasource;

import com.udpwork.ssdb.SSDB;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-15
 * Time: 上午10:02
 */
public class MySSDB extends SSDB {
    private DataSource dataSource;
    private int reusedCount = 20;//可重用次数
    private int currentReusedCount = 0;//当前重用次数
    private ThreadLocal<SSDB> cacheSSDB = null;

    MySSDB(String host,int port,int timeoutMs, DataSource dataSource,ThreadLocal<SSDB> cacheSSDB) throws Exception{
        super(host,port,timeoutMs);
        this.dataSource = dataSource;
        this.cacheSSDB = cacheSSDB;
    }

    @Override
    public void close() {
        currentReusedCount++;
        if(currentReusedCount<reusedCount){
            this.dataSource.ssdbConnectionsPool.addLast(this);
        }else {
            this.dataSource.currentCount--;
            if(cacheSSDB !=null && cacheSSDB.get() !=null)
                cacheSSDB.remove();//当连接关闭时，把缓存也干掉
            super.close();
        }
    }
}
