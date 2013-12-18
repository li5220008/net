package tcp;

import java.io.*;
import java.net.Socket;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-18
 * Time: 上午9:33
 */
public class TcpClient {
    public static void main(String[] args) throws Exception{
        if(args.length<2){
            System.out.println("Usage:java TcpClient ServerIp ServerPort");
            return;
        }
        Socket ss = new Socket(args[0],Integer.parseInt(args[1]));
        InputStream ips = ss.getInputStream();
        OutputStream ops = ss.getOutputStream();
        BufferedReader brNet = new BufferedReader(new InputStreamReader(ips));
        PrintWriter pw = new PrintWriter(ops,true);
        BufferedReader brKeyBord = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String strkeyWord = brKeyBord.readLine();
            pw.println(strkeyWord);
            if(strkeyWord.equalsIgnoreCase("quit")) {
                break;
            }
        }
        System.out.println(brNet.readLine());
        pw.close();
        brNet.close();
        brKeyBord.close();
        ss.close();
    }
}
