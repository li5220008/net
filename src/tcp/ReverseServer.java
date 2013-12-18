package tcp;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-17
 * Time: 下午1:39
 */
public class ReverseServer {
    public static void main(String[] args){
        try {
            ServerSocket ss;
            if(args.length>0){
                ss = new ServerSocket(Integer.parseInt(args[0]));
            }else {
                ss = new ServerSocket(3000);
            }
            boolean bRunnable = true;
            while(bRunnable){
                Socket s = ss.accept();
                new Thread(new Servicer(s)).start();
            }
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
