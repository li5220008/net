package tcp;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-17
 * Time: 下午2:06
 */
public class Servicer implements Runnable {

    private Socket socket;
    public Servicer(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream ips = socket.getInputStream();
            OutputStream ios = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ips));
            PrintWriter pw = new PrintWriter(ios,true);
            while(true){
                String strLine = br.readLine();
                System.out.println(strLine);
                if(strLine.equalsIgnoreCase("quit")){
                    break;
                }
                String strEcho = new StringBuffer(strLine).reverse().toString();
                pw.println(String.format("%s-->%s",strLine,strEcho));
            }
            br.close();
            pw.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
