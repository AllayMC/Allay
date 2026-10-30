package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.ddui.AllayDDUIScreenSession;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;

/**
 * {@code ServerboundDataDrivenScreenClosedPacketProcessor} handles a client close
 * notification for the player's currently active
 * data-driven UI session. Notifications for other screen kinds are ignored, and
 * a mismatched form ID is logged without removing the active screen. Matching
 * uses the session's own identity check, leaving the active session unchanged
 * when a delayed notification supplies a different form ID.
 *
 * <p>
 * For an accepted close, the player loses its active-screen reference before
 * session cleanup and close callbacks run. A callback can therefore open a
 * replacement screen without this handler subsequently clearing it. Exceptions
 * from the close handler propagate after removal; there is no rollback that
 * reattaches the previous session. The underlying session owns duplicate-close
 * handling and the interpretation of the supplied close reason.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class ServerboundDataDrivenScreenClosedPacketProcessor extends PacketProcessor<ServerboundDataDrivenScreenClosedPacket> {
    /**
     * Closes the active {@code AllayDDUIScreenSession} only when its form ID matches
     * the notification. Missing or different screen kinds are ignored, and an
     * unknown form ID is logged without changing the current screen.
     *
     * <p>
     * The active reference is removed before invoking session close handling.
     * This lets any close callback install a new screen without a later removal
     * from this handler erasing it. A close-handler failure propagates after
     * removal rather than restoring the old reference, and session cleanup
     * remains delegated to that session.
     */
    @Override
    public void handleSync(Player player, ServerboundDataDrivenScreenClosedPacket packet, long receiveTime) {
        var session = player.getActiveScreen();
        if (!(session instanceof AllayDDUIScreenSession ddSession)) {
            return;
        }
        if (!ddSession.matchesFormId(packet.getFormId())) {
            log.warn("Received DDUI close packet for unknown screen from player {}: {}", player.getOriginName(), packet);
            return;
        }

        // Detach before invoking close callbacks so a replacement screen opened
        // by a callback cannot be removed by this old notification afterward.
        player.removeActiveScreen();
        ddSession.handleClientClosed(packet.getCloseReason());
    }

    /**
     * Returns the serverbound data driven screen closed packet type handled by
     * this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED;
    }
}
