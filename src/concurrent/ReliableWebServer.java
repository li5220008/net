package concurrent;

import tcp.Servicer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-1-6
 * Time: 上午9:28
 */
public class ReliableWebServer {
    static Executor pool = Executors.newFixedThreadPool(7);

    public static void main(String[] args) throws Exception{

        ServerSocket socket = new ServerSocket(3000);

        while (true) {

            final Socket connection = socket.accept();

            Runnable r = new Runnable() {

                public void run() {

                    handleRequest(connection);

                }

            };

            pool.execute(r);

        }

    }

    private static void handleRequest(Socket connection) {
        new Servicer(connection).run();
    }
}
