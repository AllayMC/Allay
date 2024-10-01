package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerInventoryOptionsPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class SetPlayerInventoryOptionsPacketProcessor extends PacketProcessor<SetPlayerInventoryOptionsPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, SetPlayerInventoryOptionsPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_INVENTORY_OPTIONS;
    }
}
