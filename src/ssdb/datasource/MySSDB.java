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

    MySSDB(String host,int port,int timeoutMs, DataSource dataSource) throws Exception{
        super(host,port,timeoutMs);
        this.dataSource = dataSource;
    }

    @Override
    public void close() {
        currentReusedCount++;
        if(currentReusedCount<reusedCount){
            this.dataSource.ssdbConnectionsPool.addLast(this);
        }else {
            this.dataSource.currentCount--;
            super.close();
        }
    }
}
