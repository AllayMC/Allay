package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.bedrock.packet.SetEntityDataPacket;

public class PlayerActionPacketProcessor extends DataPacketProcessor<PlayerActionPacket> {

    @Override
    public void handle(EntityPlayer player, PlayerActionPacket packet) {
        if (packet.getAction() == PlayerActionType.RESPAWN) {
            var defaultWorld = Server.getInstance().getDefaultWorld();
            var dimension = defaultWorld.getDimension(0);
            var spawnPoint = defaultWorld.getWorldData().getSpawnPoint();

            dimension.getChunkService().getChunkImmediately(spawnPoint.x() >> 4, spawnPoint.z() >> 4);
            dimension.addPlayer(player, () -> {
                player.setSpawned(true);
                player.teleport(new Location3f(spawnPoint.x(), 64, spawnPoint.z(), dimension));

                player.setSprinting(false);
                player.setSneaking(false);

                player.removeAllEffects();

                player.setHealth(player.getMaxHealth());
                player.sendAttributesIfIsPlayer();

                player.getMetadata().set(EntityDataTypes.AIR_SUPPLY, (short) 400);

                var setEntityDataPacket = new SetEntityDataPacket();
                setEntityDataPacket.setRuntimeEntityId(player.getUniqueId());
                setEntityDataPacket.getMetadata().putAll(player.getMetadata().getEntityDataMap());
                setEntityDataPacket.setTick(player.getWorld().getTick());
                player.sendPacket(setEntityDataPacket);
            });
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
