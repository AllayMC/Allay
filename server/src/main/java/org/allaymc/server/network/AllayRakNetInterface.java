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
import org.cloudburstmc.netty.channel.raknet.RakChannelFactory;
import org.cloudburstmc.netty.channel.raknet.RakServerChannel;
import org.cloudburstmc.netty.channel.raknet.config.RakChannelOption;
import org.cloudburstmc.netty.channel.raknet.config.RakServerCookieMode;
import org.cloudburstmc.netty.handler.codec.raknet.common.RakSessionCodec;
import org.cloudburstmc.protocol.bedrock.BedrockPong;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.CompressionCodec;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.NoopCompression;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.SimpleCompressionStrategy;
import org.cloudburstmc.protocol.bedrock.netty.codec.packet.BedrockPacketCodec;
import org.cloudburstmc.protocol.bedrock.netty.codec.packet.BedrockPacketCodec_v3;
import org.cloudburstmc.protocol.bedrock.netty.initializer.BedrockServerInitializer;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code AllayRakNetInterface} accepts Bedrock sessions over RakNet and maintains
 * server-list advertisements for its bound UDP channels. Startup builds the
 * advertisement and binds IPv4 before optional IPv6. Each successful bind is
 * retained in {@code channels}, so a later bind failure can leave an earlier
 * listener active. Shutdown closes retained channels but does not explicitly
 * shut down the event-loop group created by startup.
 *
 * <p>
 * NetEase clients report RakNet version {@code 8} while using the version-three
 * Bedrock packet format. When NetEase support is enabled, the initializer chooses
 * that packet codec instead of the library's version-two selection and initially
 * disables compression until network-settings negotiation. Lifecycle and
 * advertisement operations use mutable unsynchronized state; callers must
 * coordinate concurrent changes and initialize {@code pong} through startup
 * before accessing advertisements.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayRakNetInterface extends AllayNetworkInterface {

    /** Server supplied to this transport's constructor. */
    protected final Server server;
    /**
     * {@code channels} retains successfully bound listeners across startup and
     * shutdown calls. Closing a channel does not remove it from this set.
     */
    protected final Set<Channel> channels;
    /** Random identity advertised by this transport instance. */
    protected final long serverId;

    // address and addressv6 retain the configured endpoints assigned by startup.
    // Startup always assigns address and assigns addressv6 only when IPv6 is
    // enabled, so a later startup can retain an earlier IPv6 endpoint.
    /** Configured IPv4 bind endpoint. */
    protected InetSocketAddress address;
    /** Most recently configured IPv6 endpoint. */
    protected InetSocketAddress addressv6;

    /**
     * {@code pong} holds mutable advertisement data after startup initializes it.
     * Advertisement getters and setters require this value to have been created.
     */
    protected BedrockPong pong;

    /**
     * Creates an unstarted RakNet transport with a fresh advertised server identity.
     * The constructor retains {@code server} and an empty listener set; startup
     * supplies addresses, advertisement data, and the networking resources.
     */
    public AllayRakNetInterface(Server server) {
        this.server = server;
        this.channels = new HashSet<>();
        this.serverId = ThreadLocalRandom.current().nextLong();
    }

    /**
     * Binds configured RakNet listeners and waits for each bind to complete.
     * Invalid thread counts or ports fail before binding. IPv4 binds before
     * optional IPv6, and later failure leaves earlier channels retained and
     * potentially active; repeated calls do not clear existing channels or stop
     * previously created event-loop groups.
     * A zero {@code networkThreadNumber} is passed through to Netty.
     */
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
        // Select matching channel and I/O implementations together; portable
        // NIO remains available when neither native transport can load here.
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
                .option(RakChannelOption.RAK_SERVER_COOKIE_MODE, RakServerCookieMode.ACTIVE)
                .option(RakChannelOption.RAK_MAX_MTU, networkSettings.raknetMaxMtu())
                .option(RakChannelOption.RAK_GUID, serverId)
                // Development builds effectively disable packet limits so local
                // development traffic does not trigger the configured RakNet rate caps.
                .option(RakChannelOption.RAK_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : networkSettings.raknetPacketLimit())
                .option(RakChannelOption.RAK_GLOBAL_PACKET_LIMIT, AllayAPI.getInstance().isDevBuild() ? Integer.MAX_VALUE : networkSettings.raknetGlobalPacketLimit())
                .group(new MultiThreadIoEventLoopGroup(networkThreadNumber, threadFactory, ioHandlerFactory))
                .childHandler(new AllayServerInitializer());

        // Retain each channel only after its bind succeeds. If IPv6 later fails,
        // the completed IPv4 listener remains available for explicit shutdown.
        this.channels.add(bootstrap.bind(address).syncUninterruptibly().channel());
        if (networkSettings.enablev6()) {
            this.channels.add(bootstrap.bind(addressv6).syncUninterruptibly().channel());
        }
    }

    /**
     * Closes every retained listener and waits uninterruptibly for each close.
     * The listener set remains populated afterward, and this method does not
     * explicitly shut down the event-loop groups allocated during startup.
     * A failed close future propagates its failure from the wait and stops
     * traversal, leaving later listeners unvisited after earlier close effects.
     */
    @Override
    public void shutdown() {
        // Wait for each close before proceeding so successful return follows
        // completion of all visited listeners' channel-close futures.
        for (var channel : channels) {
            channel.close().syncUninterruptibly();
        }
    }

    /**
     * Returns the RakNet session codec's current ping converted to integer milliseconds.
     * The supplied session must have a live child channel belonging to this
     * transport; absent or incompatible channel state is not converted to
     * {@code -1}.
     */
    @Override
    public int getPing(BedrockServerSession session) {
        var rakServerChannel = (RakServerChannel) session.getPeer().getChannel().parent();
        var childChannel = rakServerChannel.getChildChannel(session.getSocketAddress());
        var rakSessionCodec = childChannel.rakPipeline().get(RakSessionCodec.class);
        // The API returns an integer, so measurements outside that range wrap.
        return (int) rakSessionCodec.getPing();
    }

    /**
     * Returns the session channel's configured RakNet protocol version when available.
     * An absent channel option produces an empty result, allowing shared client
     * detection to distinguish missing metadata from the NetEase version.
     */
    @Override
    public OptionalInt getRakNetProtocolVersion(BedrockServerSession session) {
        Integer rakVersion = session.getPeer().getChannel().config().getOption(RakChannelOption.RAK_PROTOCOL_VERSION);
        return rakVersion == null ? OptionalInt.empty() : OptionalInt.of(rakVersion);
    }

    /**
     * Returns the primary description from the initialized server-list advertisement.
     * Callers must first start this interface so {@code pong} exists; reading
     * the description before initialization dereferences an absent advertisement.
     */
    @Override
    public String getMotd() {
        return pong.motd();
    }

    /**
     * Changes the primary description and publishes the updated advertisement.
     * Callers must initialize the interface first and coordinate advertisement
     * updates; this operation changes runtime state without updating persisted
     * server settings.
     */
    @Override
    public void setMotd(String motd) {
        pong.motd(motd);
        updatePong();
    }

    /**
     * Returns the secondary description from the initialized server-list advertisement.
     * Callers must first start this interface so {@code pong} exists; the method
     * does not load advertisement data from settings on demand.
     */
    @Override
    public String getSubMotd() {
        return pong.subMotd();
    }

    /**
     * Changes the secondary description and publishes the updated advertisement.
     * Callers must initialize the interface first and coordinate advertisement
     * updates; this operation retains the existing configured description for
     * future server starts.
     */
    @Override
    public void setSubMotd(String subMotd) {
        pong.subMotd(subMotd);
        updatePong();
    }

    /**
     * Changes the advertised player limit and publishes it to retained channels.
     * Startup must initialize {@code pong} first; the value changes server-list
     * presentation and does not change the player manager's admission limit.
     */
    public void setMaxPlayerCount(int maxPlayerCount) {
        pong.maximumPlayerCount(maxPlayerCount);
        updatePong();
    }

    /**
     * Changes the advertised player count and publishes it to retained channels.
     * Startup must initialize {@code pong} first; this setter changes presentation
     * without adding or removing players from the server's player manager.
     */
    public void setPlayerCount(int count) {
        pong.playerCount(count);
        updatePong();
    }

    /**
     * Creates advertisement data from configured descriptions, limits, and endpoints.
     * The initial player count is zero, the protocol fields use the latest
     * international codec, and disabled IPv6 is represented by port {@code -1}.
     */
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
                // Mark disabled IPv6 as unavailable instead of advertising its
                // configured port as an endpoint that clients could attempt to use.
                .ipv6Port(networkSettings.enablev6() ? networkSettings.portv6() : -1)
                .serverId(serverId);
    }

    /**
     * Publishes the current advertisement bytes to every retained listener.
     * Callers must initialize {@code pong} and coordinate access to its mutable
     * data and {@code channels}; this method does not rebuild settings or filter
     * closed channels from the set.
     */
    protected void updatePong() {
        for (var channel : channels) {
            channel.config().setOption(RakChannelOption.RAK_ADVERTISEMENT, pong.toByteBuf());
        }
    }

    /**
     * {@code AllayServerInitializer} adapts Bedrock channel setup for server
     * events and NetEase framing.
     */
    private class AllayServerInitializer extends BedrockServerInitializer {

        /**
         * Installs base framing handlers and dispatches the pipeline initialization event.
         * Enabled NetEase support then replaces initial compression for version
         * {@code 8}; listener changes to that handler can therefore be superseded
         * before later packet-codec initialization.
         */
        @Override
        protected void preInitChannel(Channel channel) throws Exception {
            super.preInitChannel(channel);
            new NettyPipelineInitEvent(channel).call();

            // NetEase sends its network-settings request without compression.
            // Keep the initial decoder uncompressed until negotiation sends the
            // response and installs the selected compression strategy.
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

        /**
         * Installs the Bedrock packet codec appropriate for the client's wire format.
         * With NetEase support enabled, RakNet version {@code 8} selects
         * {@code BedrockPacketCodec_v3}; other connections use the base
         * initializer's version mapping.
         *
         * <p>
         * NetEase's declared RakNet version would normally select the older
         * version-two format even though these clients use version-three packet
         * headers. Selecting the codec before peer creation keeps packet header
         * interpretation consistent with the actual client format and avoids
         * applying the library's legacy version-eight mapping to those packets.
         */
        @Override
        protected void initPacketCodec(Channel channel) throws Exception {
            // NetEase's version-eight handshake does not imply legacy packet
            // headers. Override the library mapping before it installs its
            // version-two codec for that handshake value.
            if (AllayServer.getSettings().networkSettings().neteaseClientSupport()) {
                int rakVersion = channel.config().getOption(RakChannelOption.RAK_PROTOCOL_VERSION);
                if (rakVersion == NETEASE_RAKNET_PROTOCOL_VERSION) {
                    channel.pipeline().addLast(BedrockPacketCodec.NAME, new BedrockPacketCodec_v3());
                    return;
                }
            }
            super.initPacketCodec(channel);
        }

        /**
         * Passes each created Bedrock session through the shared player admission path.
         */
        @Override
        protected void initSession(BedrockServerSession session) {
            initPlayerSession(session);
        }
    }
}
