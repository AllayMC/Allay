package cn.allay.server.network;

import cn.allay.api.network.NetworkServer;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
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
    protected Server server;
    protected Channel channel;

    public AllayNetworkServer(Server server) {
        this.server = server;
    }

    @Override
    public void start() {
        var settings = server.getServerSettings();
        this.pong = initPong(settings);
        this.bindAddress = new InetSocketAddress(settings.ip(), settings.port());
        this.channel = new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(NioDatagramChannel.class))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                .group(new NioEventLoopGroup())
                .childHandler(new BedrockServerInitializer() {
                    @Override
                    protected void initSession(BedrockServerSession session) {
                        session.setCodec(CODEC);
                        server.onClientConnect(session);
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

    protected BedrockPong initPong(ServerSettings settings) {
        return new BedrockPong()
                .edition("MCPE")
                .motd(settings.motd())
                .subMotd(settings.subMotd())
                .playerCount(0)
                .maximumPlayerCount(settings.maxClientCount())
                .gameType(settings.gameType().name())
                .protocolVersion(CODEC.getProtocolVersion());
    }
}
