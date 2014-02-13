package oio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 上午10:38
 */
public class FileStream {
    public static void main(String[] args)throws Exception{
        FileOutputStream out = new FileOutputStream("hello.txt");
        out.write("hello world!".getBytes());
        out.close();

        byte[] buf = new byte[1024];
        File file = new File("hello.txt");
        FileInputStream in = new FileInputStream(file);
        int len = in.read(buf);
        System.out.println(new String(buf,0,len));
    }
}
