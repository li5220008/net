package udp;

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
            String info = "你好阿!";
            DatagramPacket dp = new DatagramPacket(info.getBytes(),info.getBytes().length,InetAddress.getByName("localhost"),3000);
            ds.send(dp);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
