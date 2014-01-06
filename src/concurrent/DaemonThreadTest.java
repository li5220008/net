package concurrent;
import java.io.IOException;
import java.util.Random;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-6
 * Time: 上午10:04
 *
 * 守护线程在没有用户线程可服务时自动离开
 * 在Java中比较特殊的线程是被称为守护（Daemon）线程的低级别线程。
 * 这个线程具有最低的优先级，用于为系统中的其它对象和线程提供服务。
 * 将一个用户线程设置为守护线程的方式是在线程对象创建之前调用线程对象的setDaemon方法。
 * 典型的守护线程例子是JVM中的系统资源自动回收线程，
 * 我们所熟悉的Java垃圾回收线程就是一个典型的守护线程，
 * 当我们的程序中不再有任何运行中的Thread，
 * 程序就不会再产生垃圾，垃圾回收器也就无事可做，
 * 所以当垃圾回收线程是Java虚拟机上仅剩的线程时，Java虚拟机会自动离开。
 * 它始终在低级别的状态中运行，用于实时监控和管理系统中的可回收资源。
 * 守护进程（Daemon）是运行在后台的一种特殊进程。它独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。
 * 也就是说守护线程不依赖于终端，但是依赖于系统，与系统“同生共死”。
 * 那Java的守护线程是什么样子的呢。
 * 当JVM中所有的线程都是守护线程的时候，JVM就可以退出了；
 * 如果还有一个或以上的非守护线程则JVM不会退出。
*/
public class DaemonThreadTest extends Thread {

    public DaemonThreadTest() {
    }
    /** *//**
     * 线程的run方法，它将和其他线程同时运行
     */
    public void run(){
        for(;;){
            try{
                Thread.sleep(100);

            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
            System.out.println(new Random().nextInt(100));
        }
    }
    public static void main(String [] args){
        DaemonThreadTest test = new DaemonThreadTest();
        test.setDaemon(true);
        test.start();
        System.out.println("isDaemon = " + test.isDaemon());
        try {
            System.in.read(); // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}