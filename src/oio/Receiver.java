package oio;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 上午11:38
 */
public class Receiver extends Thread{
    private PipedInputStream in = new PipedInputStream();
    public PipedInputStream getIn(){
        return in;
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            System.out.println("the following message is from sender:\n"+new String(buf,0,len));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
