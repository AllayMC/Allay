package org.allaymc.server.network.processor;

import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.Set;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class PlayerAuthInputPacketProcessor extends PacketProcessor<PlayerAuthInputPacket> {

    protected static void handleMovement(EntityPlayer player, Vector3f newPos, Vector3f newRot) {
        var world = player.getLocation().dimension();
        world.getEntityPhysicsService().offerScheduledMove(
                player,
                new Location3f(
                        newPos.getX(), newPos.getY(), newPos.getZ(),
                        newRot.getX(), newRot.getY(), newRot.getZ(),
                        world
                )
        );
    }

    protected static void handleBlockAction(EntityPlayer player, List<PlayerBlockActionData> blockActions) {
        if (blockActions.isEmpty()) return;
        var world = player.getLocation().dimension();
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
            switch (action.getAction()) {
                case START_BREAK -> world.sendLevelEventPacket(pos, LevelEvent.BLOCK_START_BREAK, 0);
                case BLOCK_PREDICT_DESTROY -> {
                    var oldState = world.getBlockState(pos.getX(), pos.getY(), pos.getZ());
                    world.setBlockState(pos.getX(), pos.getY(), pos.getZ(), BlockAirBehavior.AIR_TYPE.getDefaultState());
                    world.sendLevelEventPacket(pos, LevelEvent.BLOCK_STOP_BREAK, 0);
                    world.sendLevelEventPacket(pos, LevelEvent.PARTICLE_DESTROY_BLOCK, oldState.blockStateHash());
                }
            }
        }
    }

    protected static void handleInputData(EntityPlayer player, Set<PlayerAuthInputData> inputData) {
        for (var input : inputData) {
            switch (input) {
                case START_SPRINTING -> player.setSprinting(true);
                case STOP_SPRINTING -> player.setSprinting(false);
                case START_SNEAKING -> player.setSneaking(true);
                case STOP_SNEAKING -> player.setSneaking(false);
                case START_SWIMMING -> player.setSwimming(true);
                case STOP_SWIMMING -> player.setSwimming(false);
                case START_GLIDING -> player.setGliding(true);
                case STOP_GLIDING -> player.setGliding(false);
                case START_CRAWLING -> player.setCrawling(true);
                case STOP_CRAWLING -> player.setCrawling(false);
            }
        }
    }

    @Override
    public void handleSync(EntityPlayer player, PlayerAuthInputPacket packet) {
        handleBlockAction(player, packet.getPlayerActions());
        handleInputData(player, packet.getInputData());
    }

    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerAuthInputPacket packet) {
        if (notReadyForInput(player)) return PacketSignal.HANDLED;
        // The pos which client sends to the server is higher than the actual coordinates (one base offset)
        handleMovement(player, packet.getPosition().sub(0, player.getBaseOffset(), 0), packet.getRotation());
        return PacketSignal.UNHANDLED;
    }

    protected boolean notReadyForInput(EntityPlayer player) {
        return !player.isInitialized() || !player.isSpawned() || player.willBeDespawnedNextTick();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_AUTH_INPUT;
    }
}
