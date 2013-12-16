import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-16
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
public class Chat extends Frame {
    List list = new List(6);
    TextField tfIP = new TextField(15);
    TextField tfDada = new TextField(20);
    DatagramSocket ds = null;
    public Chat(){
        this.add(list, "Center");
        Panel p = new Panel();
        this.add(p,"South");
        p.setLayout(new BorderLayout());
        p.add(tfIP, "West");
        p.add(tfDada,"East");
        try{
            ds = new DatagramSocket(3000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte[] buf = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(buf,1024);
                    while (true){
                        try {
                            ds.receive(dp);
                            list.add(String.format("from %s:%s", dp.getAddress().getHostName(), dp.getPort()),0);
                            list.add(new String(/*dp.getData()*/buf,0,dp.getLength()),1);
                        } catch (IOException e) {
                            if(!ds.isClosed())
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        tfDada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    byte [] buf =tfDada.getText().getBytes();
                    DatagramPacket dp = new DatagramPacket(
                            buf,
                            0,
                            buf.length,
                            InetAddress.getByName(tfIP.getText()),3000);
                    ds.send(dp);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                tfDada.setText("");
                //tfIP.setText("");
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                ds.close();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args){
        System.out.println("Starting Chat.......");
        Chat mainFrame = new Chat();
        mainFrame.setSize(300, 400);
        mainFrame.setTitle("Chat");
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }
}
