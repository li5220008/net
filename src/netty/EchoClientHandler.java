package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by free on 13-12-21.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(EchoClientHandler.class.getName());
    private final ByteBuf firstMessage;

    public EchoClientHandler(int firstMessageSize) {
        if(firstMessageSize<=0) {
            throw new IllegalArgumentException("firstMessageSize:" + firstMessageSize);
        }
        firstMessage = Unpooled.buffer(firstMessageSize);
        for(int i=0; i<firstMessage.capacity();i++){
            firstMessage.writeByte((byte)i);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(Level.WARNING,"Unexcepted exception from downstream.",true);
        ctx.close();
    }
}