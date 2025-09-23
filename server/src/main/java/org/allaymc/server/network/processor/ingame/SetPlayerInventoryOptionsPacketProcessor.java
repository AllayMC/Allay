package org.allaymc.server.network.processor.ingame;

import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerInventoryOptionsPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class SetPlayerInventoryOptionsPacketProcessor extends PacketProcessor<SetPlayerInventoryOptionsPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayerImpl player, SetPlayerInventoryOptionsPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_INVENTORY_OPTIONS;
    }
}
