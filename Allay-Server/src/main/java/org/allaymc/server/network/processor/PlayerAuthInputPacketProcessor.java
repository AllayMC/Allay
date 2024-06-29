package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.cloudburstmc.protocol.bedrock.data.LevelEvent.*;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class PlayerAuthInputPacketProcessor extends PacketProcessor<PlayerAuthInputPacket> {

    // Since block actions are processed asynchronously, a tolerance of 3 ticks is normal
    protected static final int BLOCK_BREAKING_TIME_FAULT_TOLERANCE = 3;

    protected int breakBlockX = Integer.MAX_VALUE;
    protected int breakBlockY = Integer.MAX_VALUE;
    protected int breakBlockZ = Integer.MAX_VALUE;

    protected int breakFaceId;
    protected BlockState breakBlock;

    protected long startBreakingTime; // Ticks
    protected double needBreakingTime; // Seconds
    protected double stopBreakingTime; // Ticks

    private static boolean isInvalidGameType(EntityPlayer player) {
        return player.getGameType() == GameType.CREATIVE || player.getGameType() == GameType.SPECTATOR;
    }

    protected void handleMovement(EntityPlayer player, Vector3f newPos, Vector3f newRot) {
        var world = player.getLocation().dimension();
        world.getEntityPhysicsService().offerScheduledMove(player, new Location3f(
                newPos.getX(), newPos.getY(), newPos.getZ(),
                newRot.getX(), newRot.getY(), newRot.getZ(),
                world
        ));
    }

    protected void handleBlockAction(EntityPlayer player, List<PlayerBlockActionData> blockActions) {
        if (blockActions.isEmpty()) return;
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
            // Check interact distance
            switch (action.getAction()) {
                case START_BREAK, BLOCK_CONTINUE_DESTROY -> {
                    if (!player.canInteract(pos.getX(), pos.getY(), pos.getZ())) {
                        log.warn("Player {} tried to break a block out of reach", player.getOriginName());
                        continue;
                    }
                }
            }

            switch (action.getAction()) {
                case START_BREAK -> {
                    if (isInvalidGameType(player)) continue;
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
                }
                case BLOCK_CONTINUE_DESTROY -> {
                    // When a player switches to breaking another block halfway through breaking one
                    if (isInvalidGameType(player)) continue;
                    // HACK: The client for some reason sends a meaningless BLOCK_CONTINUE_DESTROY before BLOCK_PREDICT_DESTROY, presumably a bug, so ignore it here
                    if (breakBlockX == pos.getX() && breakBlockY == pos.getY() && breakBlockZ == pos.getZ()) continue;

                    stopBreak(player);
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
                }
                case BLOCK_PREDICT_DESTROY -> {
                    if (isInvalidGameType(player)) continue;
                    completeBreak(player, pos.getX(), pos.getY(), pos.getZ());
                }
                case ABORT_BREAK -> {
                    // Digging interrupted
                    if (isInvalidGameType(player)) continue;
                    stopBreak(player);
                }
            }
        }
    }

    protected boolean isBreakingBlock() {
        return breakBlock != null;
    }

    protected void startBreak(EntityPlayer player, int x, int y, int z, int blockFaceId) {
        if (breakBlock != null) {
            log.warn("Player {} tried to start breaking a block while already breaking one", player.getOriginName());
            stopBreak(player);
        }

        if (breakBlockX == x && breakBlockY == y && breakBlockZ == z) {
            log.warn("Player {} tried to start breaking the same block twice", player.getOriginName());
            return;
        }

        breakBlockX = x;
        breakBlockY = y;
        breakBlockZ = z;

        breakFaceId = blockFaceId;
        breakBlock = player.getDimension().getBlockState(x, y, z);

        startBreakingTime = player.getWorld().getTick();
        needBreakingTime = breakBlock.getBlockType().getBlockBehavior().calculateBreakTime(breakBlock, player.getItemInHand(), player);
        stopBreakingTime = startBreakingTime + needBreakingTime * 20.0d;

        var pk = new LevelEventPacket();
        pk.setType(BLOCK_START_BREAK);
        pk.setPosition(Vector3f.from(x, y, z));
        pk.setData(toNetworkBreakTime(needBreakingTime));
        player.getCurrentChunk().addChunkPacket(pk);
        sendBreakingPracticeAndTime(player);
    }

    protected int toNetworkBreakTime(double breakTime) {
        if (breakTime == 0) return 65535;
        return (int) (65535 / (needBreakingTime * 20));
    }

    protected void stopBreak(EntityPlayer player) {
        var pk = new LevelEventPacket();
        pk.setType(BLOCK_STOP_BREAK);
        pk.setPosition(Vector3f.from(breakBlockX, breakBlockY, breakBlockZ));
        player.getCurrentChunk().addChunkPacket(pk);

        breakBlockX = Integer.MAX_VALUE;
        breakBlockY = Integer.MAX_VALUE;
        breakBlockZ = Integer.MAX_VALUE;

        breakFaceId = 0;
        breakBlock = null;

        startBreakingTime = 0;
        needBreakingTime = 0;
        stopBreakingTime = 0;
    }

    protected void completeBreak(EntityPlayer player, int x, int y, int z) {
        if (breakBlockX != x || breakBlockY != y || breakBlockZ != z) {
            log.warn("Player {} tried to complete breaking a different block", player.getOriginName());
            return;
        }

        var currentTime = player.getWorld().getTick();
        if (Math.abs(currentTime - stopBreakingTime) <= BLOCK_BREAKING_TIME_FAULT_TOLERANCE) {
            var world = player.getDimension();
            var itemInHand = player.getItemInHand();
            world.breakBlock(breakBlockX, breakBlockY, breakBlockZ, itemInHand, player);
            itemInHand.onBreakBlock(breakBlock, player);
            if (itemInHand.isBroken()) {
                player.setItemInHand(ItemTypes.AIR_TYPE.createItemStack());
            } else {
                player.sendItemInHandUpdate();
            }
        } else {
            log.warn("Mismatch block breaking complete time! Expected: {}gt, actual: {}gt", stopBreakingTime, currentTime);
        }

        stopBreak(player);
    }

    protected void sendBreakingPracticeAndTime(EntityPlayer player) {
        updateBreakingTime(player);

        var pk1 = new LevelEventPacket();
        pk1.setType(PARTICLE_CRACK_BLOCK);
        var blockFaceOffset = Objects.requireNonNull(BlockFace.fromId(breakFaceId)).getOffset();
        pk1.setPosition(Vector3f.from(breakBlockX + 0.5f + blockFaceOffset.x(), breakBlockY + 0.5f + blockFaceOffset.y(), breakBlockZ + 0.5f + blockFaceOffset.z()));
        pk1.setData(breakBlock.blockStateHash());

        var pk2 = new LevelEventPacket();
        pk2.setType(BLOCK_UPDATE_BREAK);
        pk2.setPosition(Vector3f.from(breakBlockX, breakBlockY, breakBlockZ));
        pk2.setData(toNetworkBreakTime(needBreakingTime));

        player.getCurrentChunk().addChunkPacket(pk1);
        player.getCurrentChunk().addChunkPacket(pk2);
    }

    protected void checkInteractDistance(EntityPlayer player) {
        if (!player.canInteract(breakBlockX, breakBlockY, breakBlockZ)) {
            log.warn("Player {} tried to interact with a block out of reach", player.getOriginName());
            stopBreak(player);
        }
    }

    protected void updateBreakingTime(EntityPlayer player) {
        var newBreakingTime = breakBlock.getBehavior().calculateBreakTime(breakBlock, player.getItemInHand(), player);
        if (needBreakingTime == newBreakingTime) return;
        // Breaking time has changed, make adjustments
        var currentTime = player.getWorld().getTick();
        var timeLeft = stopBreakingTime - currentTime;
        stopBreakingTime = currentTime + (timeLeft / needBreakingTime) * newBreakingTime;
        needBreakingTime = newBreakingTime;
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
                case START_JUMPING -> {
                    if (player.isDead()) return;
                    player.exhaust(player.isSprinting() ? 0.2f : 0.05f);
                }
            }
        }
    }

    @Override
    public void handleSync(EntityPlayer player, PlayerAuthInputPacket packet) {
        handleInputData(player, packet.getInputData());
    }

    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerAuthInputPacket packet) {
        if (notReadyForInput(player)) return PacketSignal.HANDLED;
        // The pos which client sends to the server is higher than the actual coordinates (one base offset)
        handleMovement(player, packet.getPosition().sub(0, player.getBaseOffset(), 0), packet.getRotation());
        handleBlockAction(player, packet.getPlayerActions());
        if (isBreakingBlock()) {
            sendBreakingPracticeAndTime(player);
            checkInteractDistance(player);
        }
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
