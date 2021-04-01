package github.veikkoroc.remote.netty;

import github.veikkoroc.utils.MyRpc2Constant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2021/3/31 12:34
 */
@Slf4j
public class NettyProvider {

    /**
     * 启动netty，监听事件
     */
    public static void providerStart(){
        // 创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 服务启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 获取管道
                            ChannelPipeline pipeline = ch.pipeline();
                            // 添加解码编码器
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 自定义处理器
                            pipeline.addLast(new NettyProviderHandler());
                        }
                    });

            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(MyRpc2Constant.PROVIDE_PORT).sync();
            log.info("====> 服务提供者监听 [{}] 端口,已经准备就绪....",MyRpc2Constant.PROVIDE_PORT);

            // 对关闭通道端口进行监听
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            log.error("====> 服务提供者启动出错:[{}]",e.getMessage());
            e.printStackTrace();
        }finally {
            // 开始关闭线程组
            log.info("----> 开始关闭线程组");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            log.info("<---- 线程组关闭完成");
        }
    }
}
