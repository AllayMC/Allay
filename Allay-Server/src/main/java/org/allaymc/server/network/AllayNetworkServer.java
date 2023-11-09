package org.allaymc.server.network;

import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerSettings;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.Getter;
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;

import java.net.InetSocketAddress;
import java.util.function.Supplier;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
@Getter
public class AllayNetworkServer implements NetworkServer {
    public static final Supplier<NioEventLoopGroup> SERVER_EVENT_GROUP = Suppliers.memoize(() -> new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Server IO #%d").setDaemon(true).build()));
    public static final Supplier<EpollEventLoopGroup> SERVER_EPOLL_EVENT_GROUP = Suppliers.memoize(() -> new EpollEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build()));

    protected static final BedrockCodec CODEC = ProtocolInfo.getDefaultPacketCodec();

    protected InetSocketAddress bindAddress;
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
        this.bindAddress = new InetSocketAddress(settings.networkSettings().ip(), settings.networkSettings().port());

        Class<? extends DatagramChannel> oclass;
        EventLoopGroup eventloopgroup;
        if (Epoll.isAvailable()) {
            oclass = EpollDatagramChannel.class;
            eventloopgroup = SERVER_EPOLL_EVENT_GROUP.get();
        } else {
            oclass = NioDatagramChannel.class;
            eventloopgroup = SERVER_EVENT_GROUP.get();
        }

        this.channel = new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(oclass))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                .group(eventloopgroup)
                .childHandler(new BedrockServerInitializer() {
                    @Override
                    protected void initSession(BedrockServerSession session) {
                        session.setLogging(true);
                        session.setCodec(CODEC);
                        server.onConnect(session);
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

    @Override
    public void updatePong() {
        this.channel.config().setOption(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf());
    }

    @Override
    public void setMotd(String motd, boolean update) {
        pong.motd(motd);
        if (update) updatePong();
    }

    @Override
    public void setSubMotd(String subMotd, boolean update) {
        pong.subMotd(subMotd);
        if (update) updatePong();
    }

    @Override
    public void setPlayerCount(int count, boolean update) {
        pong.playerCount(count);
        if (update) updatePong();
    }

    @Override
    public void setMaxPlayerCount(int maxPlayerCount, boolean update) {
        pong.maximumPlayerCount(maxPlayerCount);
        if (update) updatePong();
    }

    protected BedrockPong initPong(ServerSettings settings) {
        return new BedrockPong()
                .edition("MCPE")
                .motd(settings.genericSettings().motd())
                .subMotd(settings.genericSettings().subMotd())
                .playerCount(0)
                .maximumPlayerCount(settings.genericSettings().maxClientCount())
                .gameType(settings.genericSettings().defaultGameType().name())
                .protocolVersion(CODEC.getProtocolVersion())
                .ipv4Port(settings.networkSettings().port())
                .ipv6Port(settings.networkSettings().port());//TODO: ipv6
    }
}
