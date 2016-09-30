package start;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Date;
import java.util.List;

/**
 * @author Zheng Zhipeng
 */
public class TimeClientWithPojo {

    public static void main(String[] args) throws InterruptedException {
        String host;
        int port;
        host = args.length > 0 ? args[0] : "127.0.0.1";
        port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new TimeDecoder())
                                    .addLast(new TimeClientHandler());
                        }
                    });

            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static class TimeClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            UnixTime m = (UnixTime) msg;
            System.out.println(m);
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static class TimeDecoder extends ByteToMessageDecoder {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (in.readableBytes() < 4) {
                System.out.println("TimeDecoder doesn't collect enough bytes.");
                return;
            }
            out.add(new UnixTime(in.readUnsignedInt()));
            System.out.println("TimeDecoder collected 4 bytes.");
        }
    }

    public static class UnixTime {

        private final long value;

        public UnixTime() {
            this(System.currentTimeMillis() / 1000L + 2208988800L);
        }

        public UnixTime(long value) {
            this.value = value;
        }

        public long value() {
            return value;
        }

        @Override
        public String toString() {
            return "UnixTime: " + new Date((value() - 2208988800L) * 1000L).toString();
        }
    }
}
