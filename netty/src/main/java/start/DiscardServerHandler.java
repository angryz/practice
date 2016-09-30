package start;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Discard any messages from client.
 *
 * @author Zheng Zhipeng
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("Channel [{}] received [{}]", ctx.channel().toString(), msg.toString());
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }

        // discard the received data silently.
        //in.release();

        /*
         * ByteBuf is a reference-counted object which has to be released explicitly via
         * the release() method. Please keep in mind that it is the handler's responsibility to
         * release any reference-counted object passed to the handler.
         */
        // usually, this method is implemented like the following:
        /*
        try {
            // do sth. with the message
        } finally {
            ReferenceCountUtil.release(msg);
        }
        */
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
