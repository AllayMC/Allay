package cn.allay.server.network;

import cn.allay.api.network.NetworkProcessor;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.network.NetworkSettings;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.Getter;
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;

import java.net.InetSocketAddress;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
@Getter
public class AllayNetworkServer implements NetworkServer {

    protected static final BedrockCodec CODEC = ProtocolInfo.getDefaultPacketCodec();

    protected InetSocketAddress bindAddress;
    //TODO: Update PONG
    protected BedrockPong pong;
    protected NetworkProcessor networkProcessor;
    protected Channel channel;

    public AllayNetworkServer(NetworkProcessor networkProcessor) {
        this.networkProcessor = networkProcessor;
    }

    @Override
    public void start() {
        var networkSettings = networkProcessor.getNetworkSetting();
        this.pong = initPong(networkSettings);
        this.bindAddress = new InetSocketAddress(networkSettings.ip(), networkSettings.port());
        this.channel = new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(NioDatagramChannel.class))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                .group(new NioEventLoopGroup())
                .childHandler(new BedrockServerInitializer() {
                    @Override
                    protected void initSession(BedrockServerSession session) {
                        session.setCodec(CODEC);
                        networkProcessor.onClientConnect(session);
                    }
                })
                .bind(bindAddress)
                .syncUninterruptibly()
                .channel();
    }

    @Override
    public BedrockCodec getCodec() {
        return CODEC;
    }

    protected BedrockPong initPong(NetworkSettings networkSettings) {
        return new BedrockPong()
                .edition("MCPE")
                .motd(networkSettings.motd())
                .subMotd(networkSettings.subMotd())
                .playerCount(0)
                .maximumPlayerCount(networkSettings.maxClientCount())
                .gameType(networkSettings.gameType().name())
                .protocolVersion(CODEC.getProtocolVersion());
    }
}
