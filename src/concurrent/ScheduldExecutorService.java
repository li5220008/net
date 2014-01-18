package concurrent;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by free on 14-1-5.
 */
public class ScheduldExecutorService {
    public static void main(String[] args) throws Exception{
        /*ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture =
                scheduledExecutorService.schedule(new Callable() {
                    public Object call() throws Exception {
                        System.out.println("Executed!");
                        return "Called!";
                    }
                },
                        5,
                        TimeUnit.SECONDS);*/
        /*ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture =
                scheduledExecutorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(new Date().getTime());
                    }
                },
                        2,
                        TimeUnit.SECONDS);*/

        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture =
                //scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println(System.currentTimeMillis() + Thread.currentThread().getName());
                    }
                },
                        2, 1,
                        TimeUnit.SECONDS);

        System.out.println("result = " + scheduledFuture.get());

        scheduledExecutorService.shutdown();
    }
}
