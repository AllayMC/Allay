package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.ddui.AllayDDUIScreenSession;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataStorePacket;

/**
 * @author OpenAI
 */
@Slf4j
public final class ServerboundDataStorePacketProcessor extends PacketProcessor<ServerboundDataStorePacket> {
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

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_STORE;
    }
}
