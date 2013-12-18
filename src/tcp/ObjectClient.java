package tcp;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-12-18
 * Time: 下午5:06
 */
public class ObjectClient {
    public static void main(String[] args) throws Exception{
        Socket s = new Socket("127.0.0.1",3000);
        InputStream ips = s.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(ips);
        Student student = (Student)ois.readObject();
        System.out.println(student.toString());
    }
}
