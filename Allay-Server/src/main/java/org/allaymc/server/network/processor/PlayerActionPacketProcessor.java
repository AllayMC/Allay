package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerRespawnEvent;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

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

                new PlayerRespawnEvent(player).call();

                var spawnPoint = player.getSpawnPoint();
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
                    spawnDimension.getEntityService().addEntity(player, () -> afterRespawn(player, spawnPoint));
                }
                yield PacketSignal.HANDLED;
            }
            case DIMENSION_CHANGE_SUCCESS -> {
                player.getManager().<EntityPlayerBaseComponentImpl>getComponent(EntityBaseComponentImpl.IDENTIFIER).sendDimensionChangeSuccess();
                yield PacketSignal.HANDLED;
            }
            case DIMENSION_CHANGE_REQUEST_OR_CREATIVE_DESTROY_BLOCK -> {
                if (player.getGameType() != GameType.CREATIVE) {
                    log.warn("Player {} tried to creative destroy block in non-creative mode", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                var pos = packet.getBlockPosition();
                var world = player.getDimension();
                var oldState = world.getBlockState(pos.getX(), pos.getY(), pos.getZ());
                var pk = new LevelEventPacket();
                pk.setType(LevelEvent.PARTICLE_DESTROY_BLOCK);
                pk.setPosition(Vector3f.from(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f));
                pk.setData(oldState.blockStateHash());
                player.getCurrentChunk().addChunkPacket(pk);

                var itemInHand = player.getItemInHand();
                world.breakBlock(pos.getX(), pos.getY(), pos.getZ(), itemInHand, player);
                itemInHand.onBreakBlock(oldState, player);
                if (itemInHand.isBroken()) {
                    player.clearItemInHand();
                } else {
                    player.sendItemInHandUpdate();
                }
                yield PacketSignal.HANDLED;
            }
            case START_ITEM_USE_ON -> {
                if (player.isUsingItemOnBlock()) {
                    log.warn("Player {} tried to start item use on without stopping", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                player.setUsingItemOnBlock(true);
                yield PacketSignal.HANDLED;
            }
            case STOP_ITEM_USE_ON -> {
                if (!player.isUsingItemOnBlock()) {
                    log.warn("Player {} tried to stop item use on without starting", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                player.setUsingItemOnBlock(false);
                yield PacketSignal.HANDLED;
            }
            default -> PacketSignal.UNHANDLED;
        };
    }

    private void prepareForRespawn(EntityPlayer player, Location3ic spawnPoint) {
        var spawnDimension = spawnPoint.dimension();
        spawnDimension.getChunkService().getOrLoadChunkSync(spawnPoint.x() >> 4, spawnPoint.z() >> 4);
        player.setLocationBeforeSpawn(new Location3f(spawnPoint));
    }

    private void afterRespawn(EntityPlayer player, Location3ic spawnPoint) {
        var spawnDimension = spawnPoint.dimension();
        // Teleport to prevent the player from falling into strange places
        player.teleport(new Location3f(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), spawnDimension));
        player.setSprinting(false);
        player.setSneaking(false);
        player.removeAllEffects();
        player.resetHealth();
        player.resetFoodData();
        player.setAndSendEntityData(EntityDataTypes.AIR_SUPPLY, (short) 400);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
