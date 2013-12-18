package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-18
 * Time: 下午5:05
 */
public class ObjectServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();
        OutputStream os = s.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        Student student = new Student(11L,"jime","waiyu");
        oos.writeObject(student);
        oos.close();
    }
}
