package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-17
 * Time: 上午9:50
 */
public class TcpServer {
    public static void main(String[] args){
        try {
            ServerSocket ss = new ServerSocket(3000);
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            os.write("welcome to sky!".getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println(br.readLine());
            is.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
