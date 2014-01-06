package concurrent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-3
 * Time: 下午2:58
 */
public class SemaphoreTest1 {
    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟个客户端访问
        for (int index = 1; index < 21; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + NO);

                        Thread.sleep((long) (Math.random() * 1000));
                        // 访问完后，释放
                        System.out.println("Release: " + NO);
                        semp.release();
                    } catch (InterruptedException e) {
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
}
