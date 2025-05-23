package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;

/**
 * @author Cool_Loong
 */
public class MobEquipmentPacketProcessor extends PacketProcessor<MobEquipmentPacket> {
    @Override
    public void handleSync(EntityPlayer player, MobEquipmentPacket packet, long receiveTime) {
        player.setHandSlot(packet.getHotbarSlot(), false);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOB_EQUIPMENT;
    }
}
