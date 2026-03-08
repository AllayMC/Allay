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

/**
 * Abstract base class for {@link NetworkInterface} implementations in the server module.
 * <p>
 * Provides shared session initialization logic that can be reused by any transport
 * (RakNet, QUIC, TCP, WebSocket, etc.) that creates {@link BedrockServerSession} instances.
 * <p>
 * Subclasses must implement {@link #getPing(BedrockServerSession)} to provide
 * transport-specific ping/latency measurement.
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class AllayNetworkInterface implements NetworkInterface {

    /**
     * Start this network interface and begin accepting connections.
     * <p>
     * Called when the server starts or when this interface is registered after server start.
     */
    public void start() {}

    /**
     * Shutdown this network interface and stop accepting connections.
     * <p>
     * Called when this interface is unregistered or when the server stops.
     */
    public void shutdown() {}

    /**
     * Set the displayed player count on this interface.
     *
     * @param count the current player count
     */
    public void setPlayerCount(int count) {}

    /**
     * Set the displayed maximum player count on this interface.
     *
     * @param maxPlayerCount the maximum player count
     */
    public void setMaxPlayerCount(int maxPlayerCount) {}

    /**
     * Get the ping (round-trip latency) for the given session.
     *
     * @param session the bedrock server session
     *
     * @return the ping in milliseconds, or {@code -1} if not available
     */
    public abstract int getPing(BedrockServerSession session);

    /**
     * Initialize a player session from a {@link BedrockServerSession}.
     * <p>
     * This method extracts the shared session initialization logic (IP ban check,
     * player creation, connect event, login timeout) so that any transport can
     * feed sessions into the same path.
     *
     * @param session the bedrock server session to initialize
     */
    protected void initPlayerSession(BedrockServerSession session) {
        var server = Server.getInstance();
        var ip = AllayStringUtils.fastTwoPartSplit(session.getSocketAddress().toString().substring(1), ":", "")[0];
        if (server.getPlayerManager().isIPBanned(ip)) {
            session.disconnect(I18n.get().tr(TrKeys.ALLAY_DISCONNECT_BANIP));
            return;
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
            server.getScheduler().scheduleDelayed(server, () -> {
                var status = player.getClientState();
                if (status != ClientState.DISCONNECTED && status.ordinal() < ClientState.IN_GAME.ordinal()) {
                    log.warn("Session {} didn't log in within {} seconds, disconnecting...", session.getSocketAddress(), maxLoginTime / 20d);
                    player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_TIMEOUT);
                }
                return true;
            }, maxLoginTime);
        }

        log.info(I18n.get().tr(TrKeys.ALLAY_NETWORK_CLIENT_CONNECTED, session.getSocketAddress().toString()));
    }
}
