package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-3
 * Time: 下午1:29
 */
public class SemaphoreTest {
        public static void main(String[] args) {
            // 线程池
            ExecutorService exec = Executors.newCachedThreadPool();
            // 只能5个线程同时访问
            final Semaphore semp = new Semaphore(5);
            // 模拟20个客户端访问
            for (int index = 0; index < 20; index++) {
                final int NO = index;
                Runnable run = new Runnable() {
                    public void run() {
                        try {
                            // 获取许可
                            semp.acquire();
                            System.out.println("Accessing: " + NO);
                            Thread.sleep((long) (Math.random() * 10000));
                            // 访问完后，释放
                            semp.release();
                            //availablePermits()指的是当前信号灯库中有多少个可以被使用
                            System.out.println("-----------------" + semp.availablePermits());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                exec.execute(run);
            }
            // 退出线程池
            exec.shutdown();
        }

}

class pool{
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    // Not a particularly efficient data structure; just for demo
    protected Object[] items = {"a","b","c"};
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        if (markAsUnused(x))
            available.release();
    }

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }




}
