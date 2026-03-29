package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataStorePacket;

/**
 * Processes incoming Bedrock data store updates and forwards them to the active DDUI screen.
 *
 * @author Miroshka
 */
public final class ServerboundDataStorePacketProcessor extends PacketProcessor<ServerboundDataStorePacket> {

    /**
     * Handles an incoming data store packet for DDUI input.
     *
     * @param player receiver of the packet
     * @param packet incoming Bedrock packet
     * @param receiveTime packet receive timestamp
     */
    @Override
    public void handleSync(Player player, ServerboundDataStorePacket packet, long receiveTime) {
        var update = packet.getUpdate();
        if (update == null || update.getDataStoreName() == null || update.getProperty() == null || update.getPath() == null) {
            return;
        }

        player.handleDataDrivenScreenInput(update.getDataStoreName(), update.getProperty(), update.getPath(), update.getData());
    }

    /**
     * Returns the handled Bedrock packet type.
     *
     * @return serverbound data store packet type
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DATA_STORE;
    }
}
