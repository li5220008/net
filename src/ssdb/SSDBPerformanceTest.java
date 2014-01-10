package ssdb;

import com.tom.springutil.StopWatch;
import com.udpwork.ssdb.Response;
import com.udpwork.ssdb.SSDB;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-7
 * Time: 下午1:13
 */
public class SSDBPerformanceTest extends SSDB {
    private int defaultData = 100000;//默认创建条数
    //private int defaultData = 1000;//默认创建条数
    //private int interval = defaultData/10;//秒表间隔多少条开始计时
    private int queryNum = 10000;//并发查询多少次
    private int interval = queryNum/10;//秒表间隔多少条开始计时
    private String n = "test";//测试的集合名称
    private int number;
    public SSDBPerformanceTest(String host, int port) throws Exception{
        super(host, port);
    }
    public void createHsetData(int number) throws Exception{
        this.number = number;
        StopWatch sw = new StopWatch("创建hset数据");
        int counter = 0;
        for (int i=0;i<number;i++){
            String k = String.valueOf(i);
            //String v = String.valueOf(new Random().nextInt(1000));
            String v = String.format("news%s",i);
            if(i%interval ==0) {
                counter = i+interval-1;
                sw.start(String.format("第%s条(名称：%s 键：%s 值：%s)",counter+1,n,k,v));
            }
            hset(n, k, v);
            if(i==counter)  {
                //System.out.println(String.format("已经插入了%s条",i+1));
                sw.stop();
                queryHsetPerformance(i-interval-1+"", k, 10, i+1);
            }
        }
        System.out.println(sw.prettyPrint());
    }
    public void createHsetData() throws Exception{
        createHsetData(defaultData);
    }

    public void createZsetData(int number) throws Exception{
        this.number = number;
        StopWatch sw = new StopWatch("创建zset数据");
        int counter = 0;
        for(int i=0; i<number; i++){
            String k = String.valueOf(i);
            //long s = System.currentTimeMillis();太长会被截断
            double s = Double.valueOf(i+1);
            if(i%interval ==0) {
                counter = i+interval-1;
                sw.start(String.format("第%s条(名称：%s 键：%s 权重：%s)",counter+1,n,k,s));
            }
            zset(n, k, s);
            if(i==counter)  {
                sw.stop();
                queryZsetPerformance(Double.valueOf(i-interval-1),Double.valueOf(s),10,i+1);
            }
        }
        System.out.println(sw.prettyPrint());
    }

    public void queryHsetPerformance(String keyStart,String keyEnd,int limit,int capacity) throws Exception{
        long st = System.nanoTime();
        Response response = hscan("test", keyStart, keyEnd, limit);
        long ut = System.nanoTime() - st;
        System.out.println("====================查询性能==============================");
        System.out.println(String.format("%s条容量hscan耗时 %s（纳秒）", capacity, ut));
        System.out.println("====================查询结果==============================");
        response.print();
    }


    public void queryZsetPerformance(Double coreStart,Double coreEnd,int limit,int capacity)throws Exception{
        long st = System.nanoTime();
        Response response = zscan("test","",coreStart,coreEnd,limit);
        long ut = System.nanoTime() - st;
        System.out.println("====================查询性能==============================");
        System.out.println(String.format("%s条容量zscan耗时 %s（纳秒）", capacity, ut));
        System.out.println("====================查询结果==============================");
        response.print();
    }

    public void createData() throws Exception{
        createZsetData(defaultData);
    }

    public void query(int queryNum){
        StopWatch sw = new StopWatch("单线程查询");
        Double start = Double.valueOf(new Random().nextInt(1000));
        Double end = Double.valueOf(new Random().nextInt(1000000));
        int counter = 0;
        for(int i=0;i<queryNum;i++){
            if(i%interval==0){
                counter = i+interval-1;
                sw.start(String.format("查询%s次", i));
            }
            try {
                zscan("test","",start,end,10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(i == counter){
                sw.stop();
            }
        }
        System.out.println(sw.prettyPrint());
        System.out.println(String.format("===========qps==================="));
        System.out.println(queryNum/sw.getTotalTimeSeconds());
    }

    public void query(){
        query(this.queryNum);
    }

    public void concurrentyQuery(){
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch cdt = new CountDownLatch(queryNum);
        Terminator terminator = new Terminator(cdt,this);
        pool.execute(terminator);
        for(int i=0; i<queryNum;i++){
            pool.execute(new Queryer(cdt,this));
        }
        pool.shutdown();
    }
}
