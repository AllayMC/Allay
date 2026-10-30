package org.allaymc.server.network;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.server.PlayerConnectEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.network.NetworkInterface;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.AllayServer;
import org.allaymc.server.eventbus.event.network.NettyPipelineInitEvent;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

import java.util.OptionalInt;

/**
 * {@code AllayNetworkInterface} supplies player-session admission for server
 * transports that create {@link BedrockServerSession} instances. The admission
 * path checks an IP ban before creating a player, then publishes that player to
 * the channel attribute before dispatching the connection event. A cancelled
 * connection event disconnects the session before the login timer is installed.
 * Subclasses provide transport-specific advertisement and latency behavior.
 *
 * <p>
 * Login can progress after admission returns, so the delayed timeout observes
 * the player's state when it runs. It disconnects only clients that have not
 * reached {@code IN_GAME} and are not already {@code DISCONNECTED}. The timeout
 * does not cancel itself when login finishes; its state check determines whether
 * a later callback still needs to act.
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class AllayNetworkInterface implements NetworkInterface {

    /**
     * {@code NETEASE_RAKNET_PROTOCOL_VERSION} identifies NetEase connections when
     * a transport exposes its RakNet version; missing versions do not match.
     */
    protected static final int NETEASE_RAKNET_PROTOCOL_VERSION = 8;

    /**
     * Provides the default startup hook used by the network manager.
     * This base implementation does nothing; transports override it to accept
     * connections when networking starts or registration occurs after startup.
     */
    public void start() {}

    /**
     * Provides the default shutdown hook used by the network manager.
     * This base implementation does nothing; transports override it to release
     * their resources during unregistration or server shutdown, including resources
     * retained after partial startup.
     */
    public void shutdown() {}

    /**
     * Accepts a player-count update without changing this base implementation's state.
     */
    public void setPlayerCount(int count) {}

    /**
     * Accepts a player-limit update without changing this base implementation's state.
     */
    public void setMaxPlayerCount(int maxPlayerCount) {}

    /**
     * Returns transport-measured round-trip latency for the supplied session.
     * Implementations report milliseconds or {@code -1} when a measurement is
     * unavailable; callers must supply a session owned by the corresponding
     * transport.
     */
    public abstract int getPing(BedrockServerSession session);

    /**
     * Returns no RakNet version for transports using this default implementation.
     * Transports that expose a version override this method; an empty result
     * makes {@link #isNetEaseClient(BedrockServerSession)} treat the connection as
     * unrecognized rather than assume a NetEase client.
     */
    public OptionalInt getRakNetProtocolVersion(BedrockServerSession session) {
        return OptionalInt.empty();
    }

    /**
     * Returns whether the transport reports the NetEase RakNet version.
     * Missing version information returns {@code false}, so transports without
     * RakNet metadata can reuse session initialization without classifying every
     * client as NetEase.
     */
    public boolean isNetEaseClient(BedrockServerSession session) {
        return getRakNetProtocolVersion(session).orElse(-1) == NETEASE_RAKNET_PROTOCOL_VERSION;
    }

    /**
     * Admits a transport session through the shared player connection path.
     * The method disconnects banned addresses before player creation and stores
     * an accepted address's player in {@code PLAYER_ATTRIBUTE_KEY} before calling
     * {@code PlayerConnectEvent}. Event cancellation disconnects the session
     * using the listener-selected reason.
     *
     * <p>
     * A positive {@code maxLoginTime} schedules one delayed state check in server
     * ticks. The callback observes login progress when it executes and ignores
     * clients already in game or disconnected. Successful admission returns
     * before that callback, and the callback does not provide an atomic boundary
     * with other changes to the player's state.
     */
    protected void initPlayerSession(BedrockServerSession session) {
        var server = Server.getInstance();
        var ip = AllayStringUtils.fastTwoPartSplit(session.getSocketAddress().toString().substring(1), ":", "")[0];
        if (server.getPlayerManager().isIPBanned(ip)) {
            session.disconnect(I18n.get().tr(TrKeys.ALLAY_DISCONNECT_BANIP));
            return;
        }

        if (AllayServer.getSettings().networkSettings().debugPackets()) {
            session.setLogging(true);
        }

        var player = new AllayPlayer(session, this);
        session.getPeer().getChannel().attr(NettyPipelineInitEvent.PLAYER_ATTRIBUTE_KEY).set(player);
        var event = new PlayerConnectEvent(player, "disconnect.disconnected");
        if (!event.call()) {
            session.disconnect(event.getDisconnectReason());
            return;
        }

        var maxLoginTime = AllayServer.getSettings().networkSettings().maxLoginTime();
        if (maxLoginTime > 0) {
            // Login continues after admission, so inspect the player's later
            // state before applying the timeout to a connection that finished.
            server.getScheduler().scheduleDelayed(server, () -> {
                var status = player.getClientState();
                if (status != ClientState.DISCONNECTED && status.ordinal() < ClientState.IN_GAME.ordinal()) {
                    // Display the configured tick delay using twenty ticks per second.
                    log.warn("Session {} didn't log in within {} seconds, disconnecting...", session.getSocketAddress(), maxLoginTime / 20d);
                    player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_TIMEOUT);
                }
                return true;
            }, maxLoginTime);
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_NETWORK_CLIENT_CONNECTED, session.getSocketAddress().toString()));
    }
}
