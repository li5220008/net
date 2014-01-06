package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-3
 * Time: 下午4:53
 */
public class ExecutorServiceTest {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });



        Future future = executorService.submit(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        future.get();  //returns null if the task has finished correctly.

        executorService.shutdown();
    }
}
