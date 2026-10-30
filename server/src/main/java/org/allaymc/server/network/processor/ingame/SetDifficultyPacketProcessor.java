package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetDifficultyPacket;

/**
 * {@code SetDifficultyPacketProcessor} passes client difficulty requests to the
 * controlled entity's world data when
 * its difficulty-command permission allows them. Requests without that
 * permission have no effect. Numeric conversion and the world-data setter
 * determine the proposed value and any downstream event handling.
 *
 * @author zernix2077
 */
public class SetDifficultyPacketProcessor extends PacketProcessor<SetDifficultyPacket> {

    /**
     * Requests a world difficulty change only when the controlled entity has
     * the difficulty command permission. The numeric difficulty is converted
     * directly, including a {@code null} result for unknown values, before the
     * world-data setter handles it.
     */
    @Override
    public void handleSync(Player player, SetDifficultyPacket packet, long receiveTime) {
        if (player.getControlledEntity().hasPermission(Permissions.COMMAND_DIFFICULTY).asBoolean()) {
            player.getControlledEntity().getWorld().getWorldData().setDifficulty(Difficulty.from(packet.getDifficulty()));
        }
    }

    /**
     * Returns the set difficulty packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_DIFFICULTY;
    }
}
