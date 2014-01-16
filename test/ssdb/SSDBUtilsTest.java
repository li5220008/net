package ssdb;

import com.sun.jmx.snmp.tasks.ThreadService;
import com.udpwork.ssdb.SSDB;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-16
 * Time: 上午10:50
 */
public class SSDBUtilsTest {
    @Test
    public void test(){
        Executor pool = Executors.newFixedThreadPool(10);
        for(int i=0;i<100;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    SSDB ssdb = null;
                    try {
                        ssdb = SSDBUtil.getSSDB();
                        System.out.println(ssdb);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        ssdb.close();
                    }
                }
            };
            pool.execute(task);
        }
        /*for(int i=0;i<100;i++){
            SSDB ssdb = null;
            try {
               ssdb = SSDBUtil.getSSDB();
                System.out.println(ssdb);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ssdb.close();
            }
        }*/
    }
}
