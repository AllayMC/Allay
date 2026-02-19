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
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;
import org.allaymc.server.ServerSettings;
import org.allaymc.server.eventbus.event.network.NettyPipelineInitEvent;
import org.allaymc.server.network.multiversion.MultiVersion;
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.RakServerChannel;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.netty.handler.codec.raknet.common.RakSessionCodec;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.CompressionCodec;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.NoopCompression;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.SimpleCompressionStrategy;
import org.cloudburstmc.protocol.bedrock.netty.codec.packet.BedrockPacketCodec;
import org.cloudburstmc.protocol.bedrock.netty.codec.packet.BedrockPacketCodec_v3;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * RakNet-based implementation of {@link AllayNetworkInterface}.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayRakNetInterface extends AllayNetworkInterface {

    protected final Server server;
    protected final Set<Channel> channels;
    protected final long serverId;

    protected InetSocketAddress address;
    // Can be null if ipv6 is not enabled
    protected InetSocketAddress addressv6;

    protected BedrockPong pong;

    public AllayRakNetInterface(Server server) {
        this.server = server;
        this.channels = new HashSet<>();
        this.serverId = ThreadLocalRandom.current().nextLong();
    }

    @Override
    @SneakyThrows
    public void start() {
        var settings = AllayServer.getSettings();
        var networkSettings = settings.networkSettings();

        var networkThreadNumber = networkSettings.networkThreadNumber();
        Preconditions.checkArgument(networkThreadNumber >= 0, "networkThreadNumber must be >= 0");

        int port = networkSettings.port();
        Preconditions.checkArgument(port > 0 && port <= 65535, "The IPv4 port must be in the range 1–65535");
        this.address = new InetSocketAddress(networkSettings.ip(), port);

        if (networkSettings.enablev6()) {
            int portv6 = networkSettings.portv6();
            Preconditions.checkArgument(portv6 > 0 && portv6 <= 65535, "The IPv6 port must be in the range 1–65535");
            Preconditions.checkArgument(portv6 != port, "The IPv4 port cannot be the same as the IPv6 port");
            this.addressv6 = new InetSocketAddress(networkSettings.ipv6(), portv6);
        }

        this.pong = initPong(settings);

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
                .option(RakChannelOption.RAK_SEND_COOKIE, networkSettings.raknetSendCookie())
                .option(RakChannelOption.RAK_MAX_MTU, networkSettings.raknetMaxMtu())
                .option(RakChannelOption.RAK_GUID, serverId)
                // Integer.MAX_VALUE fixed localhost blocking address
                .option(RakChannelOption.RAK_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : networkSettings.raknetPacketLimit())
                .option(RakChannelOption.RAK_GLOBAL_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : networkSettings.raknetGlobalPacketLimit())
                .group(new MultiThreadIoEventLoopGroup(networkThreadNumber, threadFactory, ioHandlerFactory))
                .childHandler(new AllayServerInitializer());

        this.channels.add(bootstrap.bind(address).syncUninterruptibly().channel());
        if (networkSettings.enablev6()) {
            this.channels.add(bootstrap.bind(addressv6).syncUninterruptibly().channel());
        }
    }

    @Override
    public void shutdown() {
        for (var channel : channels) {
            channel.close().syncUninterruptibly();
        }
    }

    @Override
    public int getPing(BedrockServerSession session) {
        var rakServerChannel = (RakServerChannel) session.getPeer().getChannel().parent();
        var childChannel = rakServerChannel.getChildChannel(session.getSocketAddress());
        var rakSessionCodec = childChannel.rakPipeline().get(RakSessionCodec.class);
        return (int) rakSessionCodec.getPing();
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
                .gameType(genericSettings.defaultGameMode().name())
                .nintendoLimited(false)
                .version(ProtocolInfo.getLatestCodec().getMinecraftVersion())
                .protocolVersion(ProtocolInfo.getLatestCodec().getProtocolVersion())
                .ipv4Port(networkSettings.port())
                .ipv6Port(networkSettings.enablev6() ? networkSettings.portv6() : -1)
                .serverId(serverId);
    }

    protected void updatePong() {
        for (var channel : channels) {
            channel.config().setOption(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf());
        }
    }

    private class AllayServerInitializer extends BedrockServerInitializer {

        private static final int NETEASE_RAKNET_PROTOCOL_VERSION = 8;

        @Override
        @MultiVersion(version = "1.21.50-NetEase", details = "NetEase clients need NOOP compression initially for uncompressed RequestNetworkSettingsPacket")
        protected void preInitChannel(Channel channel) throws Exception {
            super.preInitChannel(channel);
            new NettyPipelineInitEvent(channel).call();

            // For NetEase clients (rakVersion 8), use NOOP compression initially because
            // RequestNetworkSettingsPacket is not compressed. The real compression strategy
            // will be set in RequestNetworkSettingsPacketProcessor after processing.
            if (AllayServer.getSettings().networkSettings().neteaseClientSupport()) {
                int rakVersion = channel.config().getOption(RakChannelOption.RAK_PROTOCOL_VERSION);
                if (rakVersion == NETEASE_RAKNET_PROTOCOL_VERSION) {
                    channel.pipeline().replace(
                            CompressionCodec.NAME,
                            CompressionCodec.NAME,
                            new CompressionCodec(new SimpleCompressionStrategy(new NoopCompression()), false)
                    );
                }
            }
        }

        @Override
        @MultiVersion(version = "1.21.50-NetEase", details = "NetEase clients use RakNet version 8 but require v3 packet codec format")
        protected void initPacketCodec(Channel channel) throws Exception {
            // NetEase clients use rakVersion 8, but their packet format is the same as
            // international clients (v3), not the old v2 format that Protocol library
            // uses for rakVersion 8 by default.
            if (AllayServer.getSettings().networkSettings().neteaseClientSupport()) {
                int rakVersion = channel.config().getOption(RakChannelOption.RAK_PROTOCOL_VERSION);
                if (rakVersion == NETEASE_RAKNET_PROTOCOL_VERSION) {
                    channel.pipeline().addLast(BedrockPacketCodec.NAME, new BedrockPacketCodec_v3());
                    return;
                }
            }
            super.initPacketCodec(channel);
        }

        @Override
        protected void initSession(BedrockServerSession session) {
            if (!AllayServer.getSettings().networkSettings().enableEncodingProtection()) {
                session.getPeer().getCodecHelper().setEncodingSettings(EncodingSettings.UNLIMITED);
            }

            initPlayerSession(session);
        }
    }
}
