package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;

/**
 * Processes DDUI close notifications and ignores transition-related client closes.
 *
 * @author Miroshka
 */
public final class ServerboundDataDrivenScreenClosedPacketProcessor extends PacketProcessor<ServerboundDataDrivenScreenClosedPacket> {

    @Override
    public void handleSync(Player player, ServerboundDataDrivenScreenClosedPacket packet, long receiveTime) {
        if (shouldIgnore(packet)) {
            return;
        }

        var formId = packet.getFormId();
        if (formId == null) {
            player.removeDataDrivenScreen();
            return;
        }

        player.removeDataDrivenScreen(formId);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED;
    }

    private boolean shouldIgnore(ServerboundDataDrivenScreenClosedPacket packet) {
        var closeReason = packet.getCloseReason();
        if (closeReason == null) {
            return false;
        }

        return switch (closeReason) {
            case PROGRAMMATIC_CLOSE, PROGRAMMATIC_CLOSE_ALL, USER_BUSY -> true;
            case CLIENT_CANCELED, INVALID_FORM -> false;
        };
    }
}
