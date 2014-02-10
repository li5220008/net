package nio;


import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-8
 * Time: 下午2:31
 */
public class FileChannel {
    public static void main(String[] args) throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("test.txt", "rw");
        java.nio.channels.FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
