package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import static org.allaymc.api.block.type.BlockTypes.AIR_TYPE;

@Slf4j
public class PlayerActionPacketProcessor extends PacketProcessor<PlayerActionPacket> {
    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerActionPacket packet) {
        return switch (packet.getAction()) {
            case RESPAWN -> {
                // 玩家还没死透
                if (!player.canBeSpawned()) yield PacketSignal.HANDLED;
                var spawnPoint = player.getSpawnPoint();
                var spawnDimension = spawnPoint.dimension();
                var oldDimension = player.getDimension();
                if (oldDimension != spawnDimension) {
                    // 重生维度和玩家当前维度不同，需要将玩家从旧的维度删除
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
                player.sendDimensionChangeSuccess();
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
                yield PacketSignal.HANDLED;
            }
            case START_ITEM_USE_ON -> {
                if (player.isInteractingBlock()) {
                    log.warn("Player {} tried to start item use on without stopping", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }
                player.setInteractingBlock(true);
                yield PacketSignal.HANDLED;
            }
            case STOP_ITEM_USE_ON -> {
                if (!player.isInteractingBlock()) {
                    log.warn("Player {} tried to stop item use on without starting", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }
                player.setInteractingBlock(false);
                yield PacketSignal.HANDLED;
            }
            default -> PacketSignal.UNHANDLED;
        };
    }

    private void prepareForRespawn(EntityPlayer player, Location3ic spawnPoint) {
        var spawnDimension = spawnPoint.dimension();
        spawnDimension.getChunkService().getOrLoadChunkSynchronously(spawnPoint.x() >> 4, spawnPoint.z() >> 4);
        player.setLocationBeforeSpawn(new Location3f(spawnPoint));
    }

    private void afterRespawn(EntityPlayer player, Location3ic spawnPoint) {
        var spawnDimension = spawnPoint.dimension();
        // tp一下防止玩家掉到奇怪的地方
        player.teleport(new Location3f(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), spawnDimension));
        player.setSprinting(false);
        player.setSneaking(false);
        player.removeAllEffects();
        player.setHealth(player.getMaxHealth());
        player.setAndSendEntityData(EntityDataTypes.AIR_SUPPLY, (short) 400);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
