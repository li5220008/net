package oio;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 下午2:09
 */
public class ByteArrayTest {
    public static void main(String[] args){
        String srcStr = "abcdefghijklmnopqrstuvwxyz";
        byte [] src = srcStr.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(src);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        transform(in, out);
        byte [] result = out.toByteArray();
        System.out.println(new String(result));

        transform(System.in,System.out);
    }

    public static void transform(InputStream in, OutputStream out) {
        int ch;
        try {
            while ((ch = in.read())!=-1){
                int upperChar = Character.toUpperCase((char)ch);
                out.write(upperChar);
            }
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
