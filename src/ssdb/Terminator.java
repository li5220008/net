package ssdb;

import com.tom.springutil.StopWatch;
import com.udpwork.ssdb.SSDB;

import java.util.concurrent.CountDownLatch;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-10
 * Time: 上午11:19
 */
public class Terminator implements Runnable {
    CountDownLatch cdt;
    SSDB ssdb;
    StopWatch sw = new StopWatch("并发查询");


    public Terminator(CountDownLatch cdt,SSDB ssdb) {
        this.cdt = cdt;
        this.ssdb = ssdb;
    }

    @Override
    public void run() {
        try {
            sw.start();
            cdt.await();
            sw.stop();
            System.out.println(sw.prettyPrint());
            terminat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void terminat() {
        ssdb.close();
    }
}
