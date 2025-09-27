package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
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
                if (!player.isDespawned()) {
                    log.warn("Player {} tried to respawn but he is already spawned!", player.getOriginName());
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
        player.removeAllEffects();
        player.resetHealth();
        player.resetFoodData();
        player.extinguish();
        player.setAirSupplyTicks(player.getAirSupplyMaxTicks());
    }

    private void afterRespawn(EntityPlayer player, Location3ic spawnPoint) {
        // Teleport to prevent the player from falling into strange places
        player.teleport(spawnPoint);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
