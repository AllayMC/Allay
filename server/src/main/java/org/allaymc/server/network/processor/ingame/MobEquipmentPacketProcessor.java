package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.container.impl.UnopenedContainerId;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;

/**
 * @author Cool_Loong
 */
public class MobEquipmentPacketProcessor extends PacketProcessor<MobEquipmentPacket> {
    @Override
    public void handleSync(Player player, MobEquipmentPacket packet, long receiveTime) {
        if (packet.getContainerId() != UnopenedContainerId.PLAYER_INVENTORY) {
            return;
        }
        // Do not send the hand slot back to the client, which is meaningless and will cause
        // the hand slot jumping back and forth client-side
        player.getControlledEntity().setHandSlot(packet.getHotbarSlot(), false);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOB_EQUIPMENT;
    }
}
