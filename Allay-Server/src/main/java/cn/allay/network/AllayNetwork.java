package cn.allay.network;

import cn.allay.network.handlers.LoginHandler;
import cn.allay.server.Server;
import cn.allay.server.ServerSettings;
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
import org.cloudburstmc.protocol.bedrock.codec.v568.Bedrock_v568;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;

import java.net.InetSocketAddress;

@Getter
public class AllayNetwork implements Network {

    protected static final BedrockCodec CODEC = Bedrock_v568.CODEC;

    protected Server server;
    protected BedrockPong pong;
    protected Channel networkServer;
    protected InetSocketAddress bindAddress;

    public AllayNetwork(Server server) {
        this.server = server;
    }

    @Override
    public void start() {
        var serverSettings = server.getServerSettings();
        this.pong = initPong(serverSettings);
        this.bindAddress = new InetSocketAddress(serverSettings.ip(), serverSettings.port());
        this.networkServer = new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(NioDatagramChannel.class))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                .group(new NioEventLoopGroup())
                .childHandler(new BedrockServerInitializer() {
                    @Override
                    protected void initSession(BedrockServerSession session) {
                        session.setPacketHandler(new LoginHandler(session));
                    }
                })
                .bind(bindAddress)
                .syncUninterruptibly()
                .channel();
    }

    @Override
    public void updatePong() {
        pong.playerCount(server.getOnlinePlayerCount());
        //TODO
        //pong.gameType()
    }

    protected BedrockPong initPong(ServerSettings settings) {
        return new BedrockPong()
                .edition("MCPE")
                .motd(settings.motd())
                .subMotd(settings.subMotd())
                .playerCount(0)
                .maximumPlayerCount(settings.maxPlayerCount())
                .gameType("Survival")
                .protocolVersion(CODEC.getProtocolVersion());
    }
}
