package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class MobEquipmentPacketProcessor extends PacketProcessor<MobEquipmentPacket> {
    @Override
    public void handleSync(EntityPlayer player, MobEquipmentPacket pk) {
        var handSlot = pk.getHotbarSlot();
        player.setHandSlot(handSlot);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOB_EQUIPMENT;
    }
}
