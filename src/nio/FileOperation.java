package nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by free on 14-1-12.
 */
public class FileOperation {
    public static void main(String[] args) throws Exception{

        FileOutputStream fout = new FileOutputStream("test.txt");
        FileChannel fcOut = fout.getChannel();
        String message = "a";
        ByteBuffer bufferOut = ByteBuffer.allocate(1024);
        bufferOut.put(message.getBytes());
        bufferOut.flip();
        fcOut.write(bufferOut);

        FileInputStream fin = new FileInputStream("README.md");
        FileChannel fcIn = fin.getChannel();
        ByteBuffer bufferIn = ByteBuffer.allocate(1024);






        while (true) {
            int r = fcIn.read(bufferIn);
            if(r==-1)
                break;
            System.out.println(r);
        }
    }
}
