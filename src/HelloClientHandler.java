import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by xujiayu on 17/12/27.
 */
public class HelloClientHandler extends SimpleChannelInboundHandler {
    private String msg;

    public HelloClientHandler(String msg) {
        this.msg = msg;
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext){
//        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("111001001011101010001100111010001011111110011011111001011000100010110110111001001011100010001110111001011000110110000001111001011000010110101101111010001011111110011011111001011000100010110110111001001011101010010010111010001011110110101100111001101011010110001011111010001010111110010101", CharsetUtil.UTF_8));
        String msg = this.msg;
        ByteBuf encoded = channelHandlerContext.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        channelHandlerContext.write(encoded);
        channelHandlerContext.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) {
//        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
        System.out.println("Client received: ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause){
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}
