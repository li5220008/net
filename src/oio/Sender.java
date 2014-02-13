package oio;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 上午11:31
 */
public class Sender extends Thread {
    private PipedOutputStream out = new PipedOutputStream();
    public PipedOutputStream getOut(){
        return out;
    }

    @Override
    public void run() {
        String strInfo = new String("hello,receiver!");
        try {
            out.write(strInfo.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
