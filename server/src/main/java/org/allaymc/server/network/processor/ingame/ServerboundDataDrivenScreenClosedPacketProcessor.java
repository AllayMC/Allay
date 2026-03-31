package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.ddui.AllayDDUIScreenSession;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;

/**
 * @author OpenAI
 */
@Slf4j
public final class ServerboundDataDrivenScreenClosedPacketProcessor extends PacketProcessor<ServerboundDataDrivenScreenClosedPacket> {
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

        player.removeActiveScreen();
        ddSession.handleClientClosed(packet.getCloseReason());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED;
    }
}
