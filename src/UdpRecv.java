import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by free on 13-12-15.
 */
public class UdpRecv {
    public static void main(String[] args){
        try {
            DatagramSocket ds = new DatagramSocket(3000);
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf,1024);
            ds.receive(dp);
            String message = new String(dp.getData(),0,dp.getLength());
            String host = dp.getAddress().getHostName();
            String port = dp.getPort()+"";
            System.out.println(String.format("信息：%s 主机：%s 端口号：%s",message,host,port));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
