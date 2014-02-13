package oio;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 上午11:06
 */
public class FileStream2 {
    public static void main(String[] args)throws Exception{
        FileWriter out = new FileWriter("hello2.txt");
        out.write("nice to see you!");
        //out.flush();
        out.close();

        char[] buf = new char[1024];
        FileReader in = new FileReader("hello2.txt");
        int len = in.read(buf);
        System.out.println(new String(buf,0,len));
        in.close();
    }
}
