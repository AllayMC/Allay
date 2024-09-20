package org.allaymc.server.network;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueDatagramChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.network.ClientConnectEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerSettings;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;

import java.net.InetSocketAddress;

import static org.allaymc.api.network.ProtocolInfo.PACKET_CODEC;

/**
 * @author daoge_cmd
 */
@Slf4j
@Getter
public class AllayNetworkServer implements NetworkServer {

    protected InetSocketAddress bindAddress;
    protected BedrockPong pong;
    protected Server server;
    protected Channel channel;

    public AllayNetworkServer(Server server) {
        this.server = server;
    }

    @Override
    public void start() {
        var settings = Server.SETTINGS;
        var nettyThreadNumber = settings.networkSettings().networkThreadNumber();
        Preconditions.checkArgument(nettyThreadNumber >= 0);

        this.pong = initPong(settings);
        this.bindAddress = new InetSocketAddress(settings.networkSettings().ip(), settings.networkSettings().port());

        var threadFactory = new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build();
        Class<? extends DatagramChannel> datagramChannelClass;
        EventLoopGroup eventLoopGroup;
        if (Epoll.isAvailable()) {
            datagramChannelClass = EpollDatagramChannel.class;
            eventLoopGroup = new EpollEventLoopGroup(nettyThreadNumber, threadFactory);
        } else if (KQueue.isAvailable()) {
            datagramChannelClass = KQueueDatagramChannel.class;
            eventLoopGroup = new KQueueEventLoopGroup(nettyThreadNumber, threadFactory);
        } else {
            datagramChannelClass = NioDatagramChannel.class;
            eventLoopGroup = new NioEventLoopGroup(nettyThreadNumber, threadFactory);
        }

        this.channel = new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(datagramChannelClass))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                .group(eventLoopGroup)
                .childHandler(new BedrockServerInitializer() {
                    @Override
                    protected void initSession(BedrockServerSession session) {
                        session.setCodec(PACKET_CODEC);

                        var server = Server.getInstance();
                        if (server.isIPBanned(AllayStringUtils.fastTwoPartSplit(session.getSocketAddress().toString().substring(1), ":", "")[0])) {
                            // TODO: I18n
                            session.disconnect("Your IP is banned!");
                            return;
                        }

                        var event = new ClientConnectEvent(session);
                        event.call();
                        if (event.isCancelled()) {
                            session.disconnect();
                            return;
                        }

                        var player = EntityTypes.PLAYER.createEntity(EntityInitInfo.builder().build());
                        log.info(I18n.get().tr(TrKeys.A_NETWORK_CLIENT_CONNECTED, session.getSocketAddress().toString()));
                        player.getManager().<EntityPlayerNetworkComponentImpl>getComponent(EntityPlayerNetworkComponentImpl.IDENTIFIER).setClientSession(session);
                    }
                })
                .bind(bindAddress)
                .syncUninterruptibly()
                .channel();
    }

    @Override
    public void shutdown() {
        channel.close();
    }

    @Override
    public BedrockCodec getCodec() {
        return PACKET_CODEC;
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
                .protocolVersion(PACKET_CODEC.getProtocolVersion())
                .ipv4Port(settings.networkSettings().port())
                .ipv6Port(settings.networkSettings().port()); // TODO: ipv6
    }
}
