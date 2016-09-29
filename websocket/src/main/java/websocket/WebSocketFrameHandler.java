package websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zheng Zhipeng
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketFrameHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        if (msg instanceof TextWebSocketFrame) {
            // send the uppercase string back.
            String req = ((TextWebSocketFrame) msg).text();
            logger.info("{} received {}", ctx.channel(), req);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(req.toUpperCase()));
        } else {
            String message = "Unsupported frame type: " + msg.getClass().getName();
            throw new UnsupportedOperationException(message);
        }
    }
}
