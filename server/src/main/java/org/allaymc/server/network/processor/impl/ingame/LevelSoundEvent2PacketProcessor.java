package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LevelSoundEvent2Packet;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class LevelSoundEvent2PacketProcessor extends PacketProcessor<LevelSoundEvent2Packet> {
    @Override
    public PacketSignal handleAsync(EntityPlayer player, LevelSoundEvent2Packet packet, long receiveTime) {
        if (player.getGameType() != GameType.SPECTATOR)
            player.getCurrentChunk().addChunkPacket(packet);
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LEVEL_SOUND_EVENT_2;
    }
}
