package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;

public class PlayerActionPacketProcessor extends DataPacketProcessor<PlayerActionPacket> {

    @Override
    public void handle(EntityPlayer player, PlayerActionPacket packet) {
        if (packet.getAction() == PlayerActionType.RESPAWN) {
            var spawnPoint = player.getSpawnPoint();
            var dimension = spawnPoint.dimension();
            dimension.getChunkService().getChunkImmediately(spawnPoint.x() >> 4, spawnPoint.z() >> 4);
            dimension.addPlayer(player, () -> {
                player.teleport(new Location3f(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), dimension));
                player.setSprinting(false);
                player.setSneaking(false);
                player.removeAllEffects();
                player.setHealth(player.getMaxHealth());
                player.setAndSendEntityData(EntityDataTypes.AIR_SUPPLY, (short) 400);
            });
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
