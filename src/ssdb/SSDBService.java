package ssdb;

import com.tom.springutil.StopWatch;
import com.udpwork.ssdb.SSDB;

import java.util.Random;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-7
 * Time: 下午1:13
 */
public class SSDBService extends SSDB {
    private int defaultData = 10000;//默认创建条数
    private int interval = 100;//秒表间隔多少条开始计时
    private String n = "test";//测试的集合名称

    public SSDBService(String host, int port) throws Exception{
        super(host, port);
    }
    public SSDBService createHsetData(int number) throws Exception{
        StopWatch sw = new StopWatch("创建hset数据");
        for (int i=0;i<number;i++){
            String k = String.valueOf(System.nanoTime());
            String v = String.valueOf(new Random().nextInt(1000));
            if(i%interval ==0)
                sw.start(String.format("开始插入名 称：%s 键：%s 值：%s",n,k,v));
            hset(n, k, v);
            if(i==i+interval-2)
                sw.stop();
        }
        System.out.println(sw.prettyPrint());
        return this;
    }
    public SSDBService createHsetData() throws Exception{
        return createHsetData(defaultData);
    }

    public SSDBService createZsetData(int number) throws Exception{
        StopWatch sw = new StopWatch("创建zset数据");
        for(int i=0; i<number; i++){
            String k = String.valueOf(i);
            //long s = System.currentTimeMillis();会被截断
            double s = new Random().nextDouble();
            if(i%interval ==0)
                sw.start(String.format("开始插入名 称：%s 键：%s 权重：%s",n,k,s));
            zset(n, k, s);
            if(i%interval ==0)
                sw.stop();
        }
        System.out.println(sw.prettyPrint());
        return this;
    }
    public SSDBService createData() throws Exception{
        return createZsetData(defaultData);
    }
}
