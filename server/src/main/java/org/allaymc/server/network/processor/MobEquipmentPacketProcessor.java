package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;

/**
 * @author Cool_Loong
 */
public class MobEquipmentPacketProcessor extends PacketProcessor<MobEquipmentPacket> {
    @Override
    public void handleSync(EntityPlayer player, MobEquipmentPacket packet, long receiveTime) {
        var handSlot = packet.getHotbarSlot();
        player.setHandSlot(handSlot);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOB_EQUIPMENT;
    }
}
