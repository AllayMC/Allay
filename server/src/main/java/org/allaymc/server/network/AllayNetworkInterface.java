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
import lombok.SneakyThrows;
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
import java.util.HashSet;
import java.util.Set;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayNetworkInterface implements NetworkInterface {

    protected final Server server;
    protected final Set<Channel> channels;
    protected InetSocketAddress address;
    protected InetSocketAddress addressv6;
    protected BedrockPong pong;

    public AllayNetworkInterface(Server server) {
        this.server = server;
        this.channels = new HashSet<>();
    }

    @SneakyThrows
    public void start() {
        var settings = Server.SETTINGS;
        var networkSettings = settings.networkSettings();

        var networkThreadNumber = networkSettings.networkThreadNumber();
        int port = networkSettings.port();
        int portv6 = networkSettings.portv6();

        Preconditions.checkArgument(networkThreadNumber >= 0, "networkThreadNumber must be >= 0");
        Preconditions.checkArgument(port > 0 && port <= 65535, "The IPv4 port must be in the range 1–65535");
        Preconditions.checkArgument(portv6 > 0 && portv6 <= 65535, "The IPv6 port must be in the range 1–65535");

        this.pong = initPong(settings);
        this.address = new InetSocketAddress(networkSettings.ip(), port);
        this.addressv6 = new InetSocketAddress(networkSettings.ipv6(), portv6);

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

        var bootstrap = new ServerBootstrap()
                .channelFactory(RakChannelFactory.server(datagramChannelClass))
                .option(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf())
                // Integer.MAX_VALUE fixed localhost blocking address
                .option(RakChannelOption.RAK_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : networkSettings.raknetPacketLimit())
                .option(RakChannelOption.RAK_GLOBAL_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : networkSettings.raknetGlobalPacketLimit())
                .group(new MultiThreadIoEventLoopGroup(networkThreadNumber, threadFactory, ioHandlerFactory))
                .childHandler(new BedrockServerInitializer() {
                    @Override
                    protected void initSession(BedrockServerSession session) {
                        if (!networkSettings.enableEncodingProtection()) {
                            session.getPeer().getCodecHelper().setEncodingSettings(EncodingSettings.UNLIMITED);
                        }

                        var ip = AllayStringUtils.fastTwoPartSplit(session.getSocketAddress().toString().substring(1), ":", "")[0];
                        if (server.getPlayerService().isIPBanned(ip)) {
                            session.disconnect(I18n.get().tr(TrKeys.ALLAY_DISCONNECT_BANIP));
                            return;
                        }

                        var event = new ClientConnectEvent(session, "disconnect.disconnected");
                        if (!event.call()) {
                            session.disconnect(event.getDisconnectReason());
                            return;
                        }

                        var player = EntityTypes.PLAYER.createEntity();
                        log.info(I18n.get().tr(TrKeys.ALLAY_NETWORK_CLIENT_CONNECTED, session.getSocketAddress().toString()));
                        ((EntityPlayerNetworkComponentImpl) ((EntityPlayerImpl) player).getPlayerNetworkComponent()).setClientSession(session);
                    }
                });

        this.channels.add(bootstrap.bind(address).syncUninterruptibly().channel());
        this.channels.add(bootstrap.bind(addressv6).syncUninterruptibly().channel());
    }

    public void shutdown() {
        for (var channel : channels) {
            channel.close().syncUninterruptibly();
        }
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
        var genericSettings = settings.genericSettings();
        var networkSettings = settings.networkSettings();
        return new BedrockPong()
                .edition("MCPE")
                .motd(genericSettings.motd())
                .subMotd(genericSettings.subMotd())
                .playerCount(0)
                .maximumPlayerCount(genericSettings.maxPlayerCount())
                .gameType(genericSettings.defaultGameType().name())
                .nintendoLimited(false)
                .version(ProtocolInfo.getLatestCodec().getMinecraftVersion())
                .protocolVersion(ProtocolInfo.getLatestCodec().getProtocolVersion())
                .ipv4Port(networkSettings.port())
                .ipv6Port(networkSettings.portv6());
    }

    protected void updatePong() {
        for (var channel : channels) {
            channel.config().setOption(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf());
        }
    }
}
