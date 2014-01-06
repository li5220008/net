package concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-6
 * Time: 上午9:54
 */
public class DaemonThreadFactory implements ThreadFactory {

    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);

        thread.setDaemon(true);

        return thread;

    }

}
