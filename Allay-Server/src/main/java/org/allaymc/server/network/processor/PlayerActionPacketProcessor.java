package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
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
                var spawnPoint = player.getSpawnPoint();
                var dimension = spawnPoint.dimension();
                dimension.getChunkService().getOrLoadChunkSynchronously(spawnPoint.x() >> 4, spawnPoint.z() >> 4);
                player.setLocationBeforeSpawn(new Location3f(spawnPoint));
                dimension.addPlayer(player, () -> {
                    player.teleport(new Location3f(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), dimension));
                    player.setSprinting(false);
                    player.setSneaking(false);
                    player.removeAllEffects();
                    player.setHealth(player.getMaxHealth());
                    player.setAndSendEntityData(EntityDataTypes.AIR_SUPPLY, (short) 400);
                });
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
                world.setBlockState(pos.getX(), pos.getY(), pos.getZ(), AIR_TYPE.getDefaultState());
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

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_ACTION;
    }
}
