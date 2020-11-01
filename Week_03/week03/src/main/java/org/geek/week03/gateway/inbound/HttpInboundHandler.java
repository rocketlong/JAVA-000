package org.geek.week03.gateway.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.geek.week03.gateway.filter.HttpRequestFilter;
import org.geek.week03.gateway.filter.HttpRequestFilterImpl;
import org.geek.week03.gateway.outbound.httpclient4.HttpOutboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);

    private HttpOutboundHandler handler;

    private HttpRequestFilter filter;

    public HttpInboundHandler(String proxyServer) {
        handler = new HttpOutboundHandler(proxyServer);
        filter = new HttpRequestFilterImpl();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            filter.filter(fullRequest, ctx);
            handler.handle(fullRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
