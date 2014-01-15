package ssdb;

import com.tom.springutil.StopWatch;
import com.udpwork.ssdb.Response;
import com.udpwork.ssdb.SSDB;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Desc: 查询线程
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-10
 * Time: 上午10:45
 */
public class Queryer implements Runnable{
    CountDownLatch cdt;
    SSDB ssdb;

    public Queryer(CountDownLatch cdt,SSDB ssdb) {
        this.cdt = cdt;
        this.ssdb = ssdb;
    }

    @Override
    public void run() {
        dowork();
    }

    private synchronized void dowork() {
        /*String start = String.valueOf(new Random().nextInt(1000));
        String end = String.valueOf(new Random().nextInt(Integer.parseInt(start)*1000));*/
        /*Double start = Double.valueOf(new Random().nextInt(1000));
        Double end = Double.valueOf(new Random().nextInt(1000000));
        Response reps = null;
        try {
            reps = ssdb.zscan("test","",Double.valueOf(start),Double.valueOf(end),10);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(cdt.getCount());
        }
        //return reps.ok();
        System.out.println(String.format("当前线程：%s,查询状态：%s 计数器：%s",Thread.currentThread().getName(),reps.status,cdt.getCount()));*/
        try {
            System.out.println(String.format("当前线程：%s 计数器：%s",Thread.currentThread().getName(),cdt.getCount()));
            //System.out.println(String.format("当前是%s",Thread.currentThread().getName()));
            //Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cdt.countDown();
    }

}
