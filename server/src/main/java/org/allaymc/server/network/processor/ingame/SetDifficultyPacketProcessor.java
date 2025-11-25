package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetDifficultyPacket;

/**
 * @author zernix2077
 */
public class SetDifficultyPacketProcessor extends PacketProcessor<SetDifficultyPacket> {

    @Override
    public void handleSync(Player player, SetDifficultyPacket packet, long receiveTime) {
        var entity = player.getControlledEntity();
        if (entity.isOperator()) {
            entity.getWorld().getWorldData().setDifficulty(Difficulty.from(packet.getDifficulty()));
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_DIFFICULTY;
    }
}