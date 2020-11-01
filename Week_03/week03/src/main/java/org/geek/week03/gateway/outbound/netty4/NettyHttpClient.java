package org.geek.week03.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.net.InetSocketAddress;
import java.net.URI;

public class NettyHttpClient {

    // key为目标host，value为目标host的连接池
    public static ChannelPoolMap<InetSocketAddress, FixedChannelPool> poolMap;

    public NettyHttpClient() {
        init();
    }

    private void init() {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class);
        poolMap = new AbstractChannelPoolMap<InetSocketAddress, FixedChannelPool>() {
            @Override
            protected FixedChannelPool newPool(InetSocketAddress key) {
                return new FixedChannelPool(bootstrap.remoteAddress(key), new ChannelPoolHandler() {
                    @Override
                    public void channelReleased(Channel ch) throws Exception {
                        // 刷新管道里的数据
                        ch.writeAndFlush(Unpooled.EMPTY_BUFFER);
                    }

                    @Override
                    public void channelAcquired(Channel ch) throws Exception {
                        System.out.println("channelAcquired......");
                    }

                    @Override
                    public void channelCreated(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpResponseDecoder());
                        pipeline.addLast(new HttpRequestEncoder());
                        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                        pipeline.addLast(new NettyHttpClientOutboundHandler());
                    }
                }, 20);
            }
        };
    }

    public void handle(InetSocketAddress address, final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        if (address == null) {
            throw new RuntimeException("InetSocketAddress can not be null");
        }
        // 从连接池中获取连接
        FixedChannelPool pool = poolMap.get(address);
        // 申请连接，没有申请到或者网络断开，返回null
        Future<Channel> f = pool.acquire();
        f.addListener((FutureListener<Channel>) future -> {
            // 给服务端发送数据
            Channel channel = future.getNow();
            ChannelFuture channelFuture = channel.writeAndFlush(fullRequest);
            channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) future1 -> {
                byte[] body = EntityUtils.toByteArray((HttpEntity) future1.getNow());

                // 连接放回连接池，这里一定记得放回去
                pool.release(channel);
            });
        });
    }

}