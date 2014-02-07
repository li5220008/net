package nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * Created by free on 14-1-12.
 */
public class FileOperation {
    public static void main(String[] args) throws Exception{


        //1,获得文件输入流
        FileInputStream fin = new FileInputStream("test.txt");
        //2,获取一个通道
        FileChannel fcIn = fin.getChannel();
        //3,创建一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
/*        bufferIn.clear();
        4,数据读到缓冲区

        bufferIn.flip();
        System.out.println(bufferIn.getInt());
        bufferIn.get(bytes);
        System.out.println(new String(bytes));*/


        FileOutputStream fout = new FileOutputStream("README.md");
        FileChannel fcout = fout.getChannel();

        while (true){
            buffer.clear();
            int r = fcIn.read(buffer);
            if(r==-1) break;
            buffer.flip();
            fcout.write(buffer);
        }
    }
}
