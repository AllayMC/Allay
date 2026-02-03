package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerRespawnEvent;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;

/**
 * @author IWareQ | daoge_cmd
 */
@Slf4j
public class RespawnPacketProcessor extends PacketProcessor<RespawnPacket> {

    @Override
    public void handleSync(Player player, RespawnPacket packet, long receiveTime) {
        if (packet.getState() != RespawnPacket.State.CLIENT_READY) {
            log.warn("Respawn state must be CLIENT_READY, but got {}", packet.getState());
            return;
        }

        var entity = player.getControlledEntity();
        if (!entity.canBeSpawned()) {
            // Wait until the entity can be spawned again
            return;
        }

        var event = new PlayerRespawnEvent(entity);
        event.setRespawnLocation(this.findSpawnPoint(entity));
        event.call();

        var spawnPoint = event.getRespawnLocation();

        // NOTICE: No need to set runtime entity id
        var respawnPacket = new RespawnPacket();
        respawnPacket.setPosition(Vector3f.from(spawnPoint.x(), spawnPoint.y(), spawnPoint.z()));
        respawnPacket.setState(RespawnPacket.State.SERVER_READY);
        player.sendPacket(respawnPacket);

        // Respawn the player at the current dimension first, reset its data and then teleport to
        // the spawn point
        ((AllayDimension) entity.getDimension()).addPlayer(player, () -> {
            resetData(entity);
            entity.teleport(spawnPoint);
        });
    }

    private Location3ic findSpawnPoint(EntityPlayer entity) {
        var spawnPoint = entity.validateAndGetSpawnPoint();
        var dimension = spawnPoint.dimension();

        var blockState = dimension.getBlockState(spawnPoint.x(), spawnPoint.y(), spawnPoint.z());
        var blockBehavior = blockState.getBlockType().getBlockBehavior();
        if (blockBehavior instanceof BlockRespawnPointComponent respawnPointComponent) {
            var respawnLocation = respawnPointComponent.onPlayerRespawn(entity, new Block(blockState, new Position3i(spawnPoint, dimension)));
            if (respawnLocation != null) {
                return respawnLocation;
            }

            var globalSpawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            entity.setSpawnPoint(globalSpawnPoint);
            return globalSpawnPoint;
        }

        return spawnPoint;
    }

    private void resetData(EntityPlayer player) {
        player.removeAllEffects();
        player.resetHealth();
        player.resetFoodData();
        player.extinguish();
        player.setAirSupplyTicks(player.getAirSupplyMaxTicks());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESPAWN;
    }
}
