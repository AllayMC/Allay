package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.ddui.AllayDDUIScreenSession;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataStorePacket;

/**
 * {@code ServerboundDataStorePacketProcessor} routes client data-store updates to
 * the player's active data-driven UI
 * session. Connections without such a session are ignored. The session
 * validates store, property, and binding identity; a rejected update is logged,
 * while exceptions from applying an accepted binding propagate.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class ServerboundDataStorePacketProcessor extends PacketProcessor<ServerboundDataStorePacket> {
    /**
     * Passes the update to the active {@code AllayDDUIScreenSession} when one exists. A
     * {@code false} result logs an invalid-update warning, other active-screen kinds
     * are ignored, and binding exceptions are not recovered by this handler.
     */
    @Override
    public void handleSync(Player player, ServerboundDataStorePacket packet, long receiveTime) {
        var session = player.getActiveScreen();
        if (!(session instanceof AllayDDUIScreenSession ddSession)) {
            return;
        }

        if (!ddSession.handleDataStoreUpdate(packet.getUpdate())) {
            log.warn("Received invalid DDUI data store update from player {}: {}", player.getOriginName(), packet.getUpdate());
        }
    }

    /**
     * Returns the serverbound data store packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_STORE;
    }
}
