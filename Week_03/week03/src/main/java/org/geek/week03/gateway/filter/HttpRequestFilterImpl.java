package org.geek.week03.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestFilterImpl implements HttpRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestFilterImpl.class);

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders headers = fullRequest.headers();
        headers.add("Nio", "Long");
    }

}
