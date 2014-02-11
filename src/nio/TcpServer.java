package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 14-2-10
 * Time: 下午5:39
 */
public class TcpServer {
    public static void main(String[] args)throws Exception{
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket socket = serverChannel.socket();
        socket.bind(new InetSocketAddress(1918));
        serverChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverChannel.register(selector,SelectionKey.OP_ACCEPT);
        while (true) {
            //选择感兴趣的事件
            selector.select();
            Set<SelectionKey> selectionKeys = selector.keys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            ByteBuffer reBuf = ByteBuffer.allocate(1024);
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try{
                    if(key.isAcceptable()) {
                        //获取到一个server通道
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        //获取到一个通道
                        SocketChannel client = channel.accept();
                        System.out.println("Accepted connection from " + client);
                        /*ByteBuffer buf = ByteBuffer.allocate(1024);
                        while (client.read(buf) != -1) {
                            //翻转准备缓冲区
                            buf.flip();
                            //循环从缓冲区读出
                            while (buf.hasRemaining()){
                                char c =(char) buf.get();
                                System.out.print(c);
                                reBuf.put((byte)changeChar(c));
                            }
                            //重置缓冲区
                            buf.clear();
                        }*/
                        if(client !=null){
                            client.configureBlocking(false);
                            SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
                            reBuf.put((byte)'o');
                            reBuf.put((byte)'k');
                            reBuf.flip();
                            key2.attach(reBuf);
                        }
                    }else if(key.isWritable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        buffer.rewind();
                        buffer.put((byte)'o');
                        buffer.put((byte)'k');
                        client.write(buffer);
                    }
                }catch(IOException ex){
                    key.cancel();
                    try{
                        key.channel().close();
                    }catch(IOException cex){}
                }
            }
        }

        //serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        /*while (true){
            SocketChannel socketChannel = serverChannel.accept();

            if(socketChannel !=null){
                ByteBuffer buf = ByteBuffer.allocate(1024);
                ByteBuffer rebuf = ByteBuffer.allocate(1024);
                while (socketChannel.read(buf)!=-1){
                    buf.flip();
                    byte[] dest = new byte[1024];
                    while (buf.hasRemaining()){
                        char c = (char)buf.get();
                        System.out.print(c);
                        rebuf.put((byte)changeChar(c));
                        //buf.get(dest);
                        //System.out.print((char)buf.get());
                    }
                    buf.clear();
                    //rebuf.put(dest);
                }
                socketChannel.write(rebuf);
            }
        }*/


        /*while (true){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            SocketChannel socketChannel = serverChannel.accept();

            if(socketChannel !=null){
                ByteBuffer buf = ByteBuffer.allocate(1024);
                while (socketChannel.read(buf)!=-1){
                    buf.flip();
                    while (buf.hasRemaining()){
                        System.out.print((char)buf.get());
                    }
                    buf.clear();
                }

                break;
            }
        }*/
        //serverChannel.close();
    }
    //大小写转换一下
    public static char changeChar(char c){
        if (c >= 'A' && c <= 'Z') {
            c += 32;
            /*System.out.println("这里的大写" + (char) (c - 32) + "被转换成了" + c);
            System.out.println("这里的大写" + (c - 32) + "被转换成了" + c);*/
        } else if (c >= 'a' && c <= 'z') {
            c -= 32;
            /*System.out.println("这里的小写" + (char) (c + 32) + "被转换成了" + c);
            System.out.println("这里的小写" + (c + 32) + "被转换成了" + c);*/
        }
        return c;
    }
}
