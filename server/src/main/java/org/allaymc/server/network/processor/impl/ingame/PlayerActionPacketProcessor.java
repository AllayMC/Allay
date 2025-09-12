package org.allaymc.server.network.processor.impl.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.data.EntityData;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerRespawnEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
@Slf4j
public class PlayerActionPacketProcessor extends PacketProcessor<PlayerActionPacket> {
    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerActionPacket packet, long receiveTime) {
        return switch (packet.getAction()) {
            case RESPAWN -> {
                if (!player.canBeSpawned()) {
                    log.warn("Player {} tried to respawn but he can't be spawned!", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                var event = new PlayerRespawnEvent(player);
                event.setRespawnLocation(player.validateAndGetSpawnPoint());
                event.call();

                var spawnPoint = event.getRespawnLocation();
                var spawnDimension = spawnPoint.dimension();
                var oldDimension = player.getDimension();
                if (oldDimension != spawnDimension) {
                    // Respawn dimension and player's current dimension are different, need to remove player from the old dimension
                    oldDimension.removePlayer(player, () -> {
                        prepareForRespawn(player, spawnPoint);
                        spawnDimension.addPlayer(player, () -> afterRespawn(player, spawnPoint));
                    });
                } else {
                    prepareForRespawn(player, spawnPoint);
                    spawnDimension.getEntityManager().addEntity(player, () -> afterRespawn(player, spawnPoint));
                }
                yield PacketSignal.HANDLED;
            }
            case START_ITEM_USE_ON -> {
                if (player.isUsingItemOnBlock()) {
                    log.debug("Player {} tried to start item use on without stopping", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                player.setUsingItemOnBlock(true);
                yield PacketSignal.HANDLED;
            }
            case STOP_ITEM_USE_ON -> {
                if (!player.isUsingItemOnBlock()) {
                    log.debug("Player {} tried to stop item use on without starting", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                player.setUsingItemOnBlock(false);
                yield PacketSignal.HANDLED;
            }
            default -> PacketSignal.UNHANDLED;
        };
    }

    private void prepareForRespawn(EntityPlayer player, Location3ic spawnPoint) {
        player.setLocationBeforeSpawn(new Location3d(spawnPoint));
    }

    private void afterRespawn(EntityPlayer player, Location3ic spawnPoint) {
        var spawnDimension = spawnPoint.dimension();
        // Teleport to prevent the player from falling into strange places
        player.teleport(new Location3d(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), spawnDimension));
        player.setSprinting(false);
        player.setSneaking(false);
        player.removeAllEffects();
        player.resetHealth();
        player.resetFoodData();
        player.setData(EntityData.AIR_SUPPLY, player.getData(EntityData.AIR_SUPPLY_MAX));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
