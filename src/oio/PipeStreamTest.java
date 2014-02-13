package oio;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-13
 * Time: 下午1:19
 */
public class PipeStreamTest {
    public static void main(String[] args) throws Exception{
        Sender t1 = new Sender();
        Receiver t2 = new Receiver();
        PipedOutputStream out = t1.getOut();
        PipedInputStream in = t2.getIn();
        out.connect(in);

        t1.start();
        t2.start();
    }
}
