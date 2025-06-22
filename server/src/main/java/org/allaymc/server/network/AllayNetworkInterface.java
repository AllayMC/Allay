package org.allaymc.server.network;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.IoHandlerFactory;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollIoHandler;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueDatagramChannel;
import io.netty.channel.kqueue.KQueueIoHandler;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.network.ClientConnectEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.NetworkInterface;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerSettings;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;

import java.net.InetSocketAddress;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayNetworkInterface implements NetworkInterface {

    protected InetSocketAddress bindAddress;
    protected BedrockPong pong;
    protected Server server;
    protected Channel channel;

    public AllayNetworkInterface(Server server) {
        this.server = server;
    }

    public void start() {
        var settings = Server.SETTINGS;
        var networkThreadNumber = settings.networkSettings().networkThreadNumber();
        Preconditions.checkArgument(networkThreadNumber >= 0);

        this.pong = initPong(settings);
        this.bindAddress = new InetSocketAddress(settings.networkSettings().ip(), settings.networkSettings().port());

        var threadFactory = new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build();
        Class<? extends DatagramChannel> datagramChannelClass;
        IoHandlerFactory ioHandlerFactory;
        if (Epoll.isAvailable()) {
            datagramChannelClass = EpollDatagramChannel.class;
            ioHandlerFactory = EpollIoHandler.newFactory();
        } else if (KQueue.isAvailable()) {
            datagramChannelClass = KQueueDatagramChannel.class;
            ioHandlerFactory = KQueueIoHandler.newFactory();
        } else {
            datagramChannelClass = NioDatagramChannel.class;
            ioHandlerFactory = NioIoHandler.newFactory();
        }

        this.channel = new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(datagramChannelClass))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                // Integer.MAX_VALUE fixed localhost blocking address
                .option(RakChannelOption.RAK_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : settings.networkSettings().raknetPacketLimit())
                .option(RakChannelOption.RAK_GLOBAL_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : settings.networkSettings().raknetGlobalPacketLimit())
                .group(new MultiThreadIoEventLoopGroup(networkThreadNumber, threadFactory, ioHandlerFactory))
                .childHandler(new BedrockServerInitializer() {
                    @Override
                    protected void initSession(BedrockServerSession session) {
                        session.setCodec(ProtocolInfo.PACKET_CODEC);
                        if (!Server.SETTINGS.networkSettings().enableEncodingProtection()) {
                            session.getPeer().getCodecHelper().setEncodingSettings(EncodingSettings.UNLIMITED);
                        }

                        var server = Server.getInstance();
                        if (server.getPlayerService().isIPBanned(AllayStringUtils.fastTwoPartSplit(session.getSocketAddress().toString().substring(1), ":", "")[0])) {
                            // TODO: I18n
                            session.disconnect("Your IP is banned!");
                            return;
                        }

                        var event = new ClientConnectEvent(session, "disconnect.disconnected");
                        if (!event.call()) {
                            session.disconnect(event.getDisconnectReason());
                            return;
                        }

                        var player = EntityTypes.PLAYER.createEntity();
                        log.info(I18n.get().tr(TrKeys.A_NETWORK_CLIENT_CONNECTED, session.getSocketAddress().toString()));
                        ((EntityPlayerNetworkComponentImpl) ((EntityPlayerImpl) player).getPlayerNetworkComponent()).setClientSession(session);
                    }
                })
                .bind(bindAddress)
                .syncUninterruptibly()
                .channel();
    }

    public void shutdown() {
        channel.close().syncUninterruptibly();
    }

    @Override
    public String getMotd() {
        return pong.motd();
    }

    @Override
    public void setMotd(String motd) {
        pong.motd(motd);
        updatePong();
    }

    @Override
    public String getSubMotd() {
        return pong.subMotd();
    }

    @Override
    public void setSubMotd(String subMotd) {
        pong.subMotd(subMotd);
        updatePong();
    }

    public int getMaxPlayerCount() {
        return pong.maximumPlayerCount();
    }

    public void setMaxPlayerCount(int maxPlayerCount) {
        pong.maximumPlayerCount(maxPlayerCount);
        updatePong();
    }

    public void setPlayerCount(int count) {
        pong.playerCount(count);
        updatePong();
    }

    protected BedrockPong initPong(ServerSettings settings) {
        return new BedrockPong()
                .edition("MCPE")
                .motd(settings.genericSettings().motd())
                .subMotd(settings.genericSettings().subMotd())
                .playerCount(0)
                .maximumPlayerCount(settings.genericSettings().maxPlayerCount())
                .gameType(settings.genericSettings().defaultGameType().name())
                .version(ProtocolInfo.getMinecraftVersionStr())
                .protocolVersion(ProtocolInfo.PACKET_CODEC.getProtocolVersion())
                .ipv4Port(settings.networkSettings().port())
                .ipv6Port(settings.networkSettings().port()); // TODO: ipv6
    }

    protected void updatePong() {
        this.channel.config().setOption(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf());
    }
}
