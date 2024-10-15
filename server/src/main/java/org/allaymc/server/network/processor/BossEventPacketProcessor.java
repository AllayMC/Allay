package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class BossEventPacketProcessor extends PacketProcessor<BossEventPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, BossEventPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BOSS_EVENT;
    }
}
