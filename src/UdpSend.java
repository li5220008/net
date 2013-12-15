import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by free on 13-12-15.
 */
public class UdpSend {
    public static void main(String[] args){
        try {
            DatagramSocket ds = new DatagramSocket(3999);
            String info = "how do you do!";
            DatagramPacket dp = new DatagramPacket(info.getBytes(),info.getBytes().length,InetAddress.getByName("192.168.2.103"),3000);
            ds.send(dp);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
