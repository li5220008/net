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
        /*System.out.println((byte)'A');
        System.out.println((byte)'\t');*/
        /*for(byte i=' ';i<'~';i++){
            System.out.println(i);
        }*/
        /*System.out.println((byte)' ');
        System.out.println((byte)'~');*/
        //System.out.println(33-' ');
        //读取文件
        RandomAccessFile aFile = new RandomAccessFile(".gitignore", "rw");
        //获取通道
        java.nio.channels.FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);


        /*System.out.println("Read " + bytesRead);
        buf.flip();
        while (buf.hasRemaining()){
            System.out.print((char)buf.get());
        }
        buf.clear();*/

        while (inChannel.read(buf)!=-1) {

            buf.flip();

            while(buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            //buf.compact();
            buf.clear();
        }
        aFile.close();
    }
}
