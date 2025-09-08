package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetDifficultyPacket;
import org.allaymc.api.world.Difficulty;

/**
 * @author Zernix2077
 */
public class SetDifficultyPacketProcessor extends PacketProcessor<SetDifficultyPacket> {

    @Override
    public void handleSync(EntityPlayer player, SetDifficultyPacket packet, long receiveTime) {
        if (!player.isOperator()) return;
        player.getWorld().getWorldData().setDifficulty(Difficulty.from(packet.getDifficulty()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_DIFFICULTY;
    }
}
