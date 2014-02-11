package nio;

import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Set;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-10
 * Time: 下午2:54
 */
public class SelectorTest {
    public static void main(String[] args)throws Exception{

        ServerSocketChannel channel = ServerSocketChannel.open();
        ServerSocket serverSocket = channel.socket();
        InetSocketAddress address = new InetSocketAddress(3399);
        serverSocket.bind(address);
        channel.configureBlocking(false);

        Selector selector = Selector.open();

        SelectionKey keys = channel.register(selector, SelectionKey.OP_READ);


        while(true) {

            int readyChannels = selector.select();

            if(readyChannels == 0) continue;


            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while(keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();

                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.

                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.

                } else if (key.isReadable()) {
                    // a channel is ready for reading

                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }

                keyIterator.remove();
            }
        }
    }
}
