package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Created by free on 13-12-21.
 */
public class EchoClient {
    private final String host;
    private final int port;
    private final int firstMessage;

    public EchoClient(String host, int port, int firstMessage) {
        this.host = host;
        this.port = port;
        this.firstMessage = firstMessage;

    }

    public void run(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new EchoClientHandler(firstMessage));
                    }
                });
    }

    public static void main(String[] args){
        if(args.length<2 || args.length>3){
            System.err.println("Usage:"+EchoClient.class.getName()+"<host> <port> [<first message size>]");
            return;
        }
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        final int firstMessageSize;
        if(args.length ==3){
            firstMessageSize = Integer.parseInt(args[3]);
        }else{
            firstMessageSize =256;
        }
    }


}
