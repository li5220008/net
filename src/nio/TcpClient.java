package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-10
 * Time: 下午4:51
 */
public class TcpClient {
    public static void main(String[] args)throws Exception{
        InetSocketAddress address = new InetSocketAddress(1918);
        SocketChannel socketChannel  = SocketChannel.open(address);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        WritableByteChannel out = Channels.newChannel(System.out);
        while (socketChannel.read(buf)!=-1){
            buf.flip();
            out.write(buf);
            buf.clear();
        }
        socketChannel.close();
    }
}
