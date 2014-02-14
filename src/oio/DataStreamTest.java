package oio;

import java.io.*;
import java.sql.SQLOutput;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-14
 * Time: 上午9:32
 */
public class DataStreamTest {
    public static void main(String[] args)throws Exception {
        FileOutputStream fos = new FileOutputStream("count.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF("ab中国");
        dos.writeBytes("ab中国");
        dos.writeChars("ab中国");
        dos.close();

        FileInputStream fis = new FileInputStream("count.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);
        System.out.println(dis.readUTF());
        byte[] buf = new byte[1024];
        int len = dis.read(buf);
        System.out.println(new String(buf,0,len));
        fis.close();
    }
}
