package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.allaymc.api.block.type.BlockTypes.AIR_TYPE;
import static org.cloudburstmc.protocol.bedrock.data.LevelEvent.*;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class PlayerAuthInputPacketProcessor extends PacketProcessor<PlayerAuthInputPacket> {

    protected void handleMovement(EntityPlayer player, Vector3f newPos, Vector3f newRot) {
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

    protected void handleBlockAction(EntityPlayer player, List<PlayerBlockActionData> blockActions) {
        if (blockActions.isEmpty()) return;
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
            switch (action.getAction()) {
                case START_BREAK -> {
                    if (isInvalidGameType(player)) continue;
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
                }
                case BLOCK_CONTINUE_DESTROY -> {
                    // 当玩家破坏一个方块一半时转而破坏另一个方块
                    if (isInvalidGameType(player)) continue;
                    // HACK: 客户端不知道为什么会在BLOCK_PREDICT_DESTROY前发个无意义的BLOCK_CONTINUE_DESTROY，应该是bug，这里忽略掉
                    if (beBreakingBlockX == pos.getX() && beBreakingBlockY == pos.getY() && beBreakingBlockZ == pos.getZ()) continue;
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
                }
                case BLOCK_PREDICT_DESTROY -> {
                    if (isInvalidGameType(player)) continue;
                    completeBreak(player, pos.getX(), pos.getY(), pos.getZ());
                }
                case ABORT_BREAK -> {
                    // 挖掘中断
                    if (isInvalidGameType(player)) continue;
                    stopBreak(player);
                }
            }
        }
    }

    private static boolean isInvalidGameType(EntityPlayer player) {
        return player.getGameType() == GameType.CREATIVE || player.getGameType() == GameType.SPECTATOR;
    }

    protected int beBreakingBlockX = Integer.MAX_VALUE;
    protected int beBreakingBlockY = Integer.MAX_VALUE;
    protected int beBreakingBlockZ = Integer.MAX_VALUE;
    protected int beBreakingFaceId;
    protected BlockState beBreakingBlock;
    protected long startBreakingTime;
    protected double neededBreakingTime;

    protected boolean isBreakingBlock() {
        return beBreakingBlock != null;
    }

    protected void startBreak(EntityPlayer player, int x, int y, int z, int blockFaceId) {
        if (beBreakingBlock != null) {
            log.warn("Player {} tried to start breaking a block while already breaking one", player.getOriginName());
            stopBreak(player);
        }
        if (beBreakingBlockX == x && beBreakingBlockY == y && beBreakingBlockZ == z) {
            log.warn("Player {} tried to start breaking the same block twice", player.getOriginName());
            return;
        }
        beBreakingBlockX = x;
        beBreakingBlockY = y;
        beBreakingBlockZ = z;
        beBreakingFaceId = blockFaceId;
        beBreakingBlock = player.getDimension().getBlockState(x, y, z);
        startBreakingTime = player.getWorld().getTick();
        neededBreakingTime = beBreakingBlock.getBlockType().getBlockBehavior().calculateBreakTime(beBreakingBlock, player.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand(), player);
        var pk = new LevelEventPacket();
        pk.setType(BLOCK_START_BREAK);
        pk.setPosition(Vector3f.from(x, y, z));
        pk.setData((int) (65535 / (neededBreakingTime * 20)));
        player.getCurrentChunk().addChunkPacket(pk);
        broadcastBreakingPracticeAndTime(player);
    }

    protected void stopBreak(EntityPlayer player) {
        var pk = new LevelEventPacket();
        pk.setType(BLOCK_STOP_BREAK);
        pk.setPosition(Vector3f.from(beBreakingBlockX, beBreakingBlockY, beBreakingBlockZ));
        pk.setData(0);
        player.getCurrentChunk().addChunkPacket(pk);
        beBreakingBlockX = Integer.MAX_VALUE;
        beBreakingBlockY = Integer.MAX_VALUE;
        beBreakingBlockZ = Integer.MAX_VALUE;
        beBreakingFaceId = 0;
        beBreakingBlock = null;
        startBreakingTime = 0;
        neededBreakingTime = 0;
    }

    protected static final int BLOCK_BREAKING_TIME_FAULT_TOLERANCE = 5;

    protected void completeBreak(EntityPlayer player, int x, int y, int z) {
        if (beBreakingBlockX != x || beBreakingBlockY != y || beBreakingBlockZ != z) {
            log.warn("Player {} tried to complete breaking a different block", player.getOriginName());
            return;
        }
        var currentTime = player.getWorld().getTick();
        var actualBlockBreakingTime = currentTime - startBreakingTime;
        if (Math.abs(actualBlockBreakingTime - neededBreakingTime * 20L) <= BLOCK_BREAKING_TIME_FAULT_TOLERANCE) {
            var world = player.getDimension();
            var oldState = world.getBlockState(beBreakingBlockX, beBreakingBlockY, beBreakingBlockZ);
            var pk = new LevelEventPacket();
            pk.setType(LevelEvent.PARTICLE_DESTROY_BLOCK);
            pk.setPosition(Vector3f.from(beBreakingBlockX + 0.5f, beBreakingBlockY + 0.5f, beBreakingBlockZ + 0.5f));
            pk.setData(oldState.blockStateHash());
            player.getCurrentChunk().addChunkPacket(pk);
            world.setBlockState(beBreakingBlockX, beBreakingBlockY, beBreakingBlockZ, AIR_TYPE.getDefaultState());
        } else {
            log.warn("Mismatch block breaking time! Expected: {}gt, actual: {}gt", neededBreakingTime * 20, actualBlockBreakingTime);
        }
        stopBreak(player);
    }

    protected void broadcastBreakingPracticeAndTime(EntityPlayer player) {
        var pk1 = new LevelEventPacket();
        pk1.setType(PARTICLE_CRACK_BLOCK);
        var blockFaceOffset = Objects.requireNonNull(BlockFace.fromId(beBreakingFaceId)).getOffset();
        pk1.setPosition(Vector3f.from(beBreakingBlockX + 0.5f + blockFaceOffset.x(), beBreakingBlockY + 0.5f + blockFaceOffset.y(), beBreakingBlockZ + 0.5f + blockFaceOffset.z()));
        pk1.setData(beBreakingBlock.blockStateHash());

        var pk2 = new LevelEventPacket();
        pk2.setType(BLOCK_UPDATE_BREAK);
        pk2.setPosition(Vector3f.from(beBreakingBlockX, beBreakingBlockY, beBreakingBlockZ));
        pk2.setData((int) (65535 / (neededBreakingTime * 20)));

        player.getCurrentChunk().addChunkPacket(pk1);
        player.getCurrentChunk().addChunkPacket(pk2);
    }

    protected void handleInputData(EntityPlayer player, Set<PlayerAuthInputData> inputData) {
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
        if (isBreakingBlock()) broadcastBreakingPracticeAndTime(player);
        handleInputData(player, packet.getInputData());
    }

    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerAuthInputPacket packet) {
        if (notReadyForInput(player)) return PacketSignal.HANDLED;
        // The pos which client sends to the server is higher than the actual coordinates (one base offset)
        handleMovement(player, packet.getPosition().sub(0, player.getBaseOffset(), 0), packet.getRotation());
        if (isBreakingBlock()) broadcastBreakingPracticeAndTime(player);
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
