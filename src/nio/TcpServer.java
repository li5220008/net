package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
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
                        //printChannel(client);
                        client.configureBlocking(false);
                        //List arrays = Arrays.asList(1,2,3,4);
                        ByteBuffer buffer = ByteBuffer.wrap("hell word!".getBytes());

                        client.register(selector, SelectionKey.OP_READ,buffer);
                    }
                    if(key.isReadable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        client.register(selector, SelectionKey.OP_WRITE,buffer);
                        // 改变自身关注事件，可以用位或操作|组合时间
                        //key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    if(key.isWritable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        //List list = (List)key.attachment();
                        //System.out.println(list);
                        //buffer.clear();
                        //ByteBuffer buffer = ByteBuffer.wrap("ok".getBytes());
                        //ByteBuffer buffer = ByteBuffer.allocate(100);
                        /*while (client.read(buffer)!=-1){
                            buffer.clear();
                            client.write(buffer);
                        }*/
                        //printBuffer(buffer);
                        buffer.clear();
                        client.write(buffer);
                        key.interestOps(SelectionKey.OP_READ);
                        /*SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer dummyResponse = ByteBuffer.wrap("ok".getBytes("UTF-8"));

                        socketChannel.write(dummyResponse);
                        if (dummyResponse.remaining() > 0) {
                            System.err.print("Filled UP");
                        }

                        key.interestOps(SelectionKey.OP_READ);*/
                    }
                }catch(IOException ex){
                    key.cancel();
                    try{
                        key.channel().close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
       /* Selector selector = Selector.open();
// 创建一个套接字通道，注意这里必须使用无参形式
        SocketChannel channel = SocketChannel.open();
// 设置为非阻塞模式，这个方法必须在实际连接之前调用(所以open的时候不能提供服务器地址，否则会自动连接)
        channel.configureBlocking(false);
// 连接服务器，由于是非阻塞模式，这个方法会发起连接请求，并直接返回false(阻塞模式是一直等到链接成功并返回是否成功)
        channel.connect(new InetSocketAddress("127.0.0.1", 7777));
// 注册关联链接状态
        channel.register(selector, SelectionKey.OP_CONNECT);
        while (true) {
            // 前略 和服务器端的类似
            // ...
            // 获取发生了关注时间的Key集合，每个SelectionKey对应了注册的一个通道
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys) {
                // OP_CONNECT 两种情况，链接成功或失败这个方法都会返回true
                if (key.isConnectable()) {
                    // 由于非阻塞模式，connect只管发起连接请求，finishConnect()方法会阻塞到链接结束并返回是否成功
                    // 另外还有一个isConnectionPending()返回的是是否处于正在连接状态(还在三次握手中)
                    if (channel.finishConnect()) {
                        // 链接成功了可以做一些自己的处理，略
                        // ...
                        // 处理完后必须吧OP_CONNECT关注去掉，改为关注OP_READ
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
                // 后略 和服务器端的类似
                // ...
            }
        }*/



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

    private static void printChannel(SocketChannel client) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (client.read(buf) != -1) {
            //翻转准备缓冲区
            buf.flip();
            //循环从缓冲区读出
            while (buf.hasRemaining()){
                char c =(char) buf.get();
                System.out.print(c);
            }
            //重置缓冲区
            buf.clear();
        }
    }
    private static void printBuffer(ByteBuffer buffer){
        if(buffer !=null){
            //翻转准备缓冲区
            //buffer.flip();
            //循环从缓冲区读出
            while (buffer.hasRemaining()){
                char c =(char) buffer.get();
                System.out.print(c);
            }
            //重置缓冲区
            buffer.clear();


        }

    }

    //大小写转换一下
    public static char changeChar(char c){
        /*if (c >= 'A' && c <= 'Z') {
            c += 32;
            *//*System.out.println("这里的大写" + (char) (c - 32) + "被转换成了" + c);
            System.out.println("这里的大写" + (c - 32) + "被转换成了" + c);*//*
        } else if (c >= 'a' && c <= 'z') {
            c -= 32;
            *//*System.out.println("这里的小写" + (char) (c + 32) + "被转换成了" + c);
            System.out.println("这里的小写" + (c + 32) + "被转换成了" + c);*//*
        }
        return c;*/
        return Character.toUpperCase(c);
    }
}
