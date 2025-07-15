package org.allaymc.server.network.processor.impl.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.world.service.AllayEntityPhysicsService;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.cloudburstmc.protocol.bedrock.data.LevelEvent.*;

/**
 * @author Cool_Loong
 */
@Slf4j
public class PlayerAuthInputPacketProcessor extends PacketProcessor<PlayerAuthInputPacket> {

    // TODO: Accurate breaking time calculations when player keeping jumping, maybe use BLOCK_BREAKING_DELAY_ENABLED?
    // The current implementation will work fine in most cases
    // But it doesn't work out the same breaking time as the client when player keep jumping
    // It is hard for us to calculate the exact breaking time when player keep jumping
    protected static final int BLOCK_BREAKING_TIME_FAULT_TOLERANCE = Integer.MAX_VALUE;
    protected static final int TELEPORT_ACK_DIFF_TOLERANCE = 1;

    protected int blockToBreakX = Integer.MAX_VALUE;
    protected int blockToBreakY = Integer.MAX_VALUE;
    protected int blockToBreakZ = Integer.MAX_VALUE;

    protected int faceToBreak;
    protected BlockState blockToBreak;

    // Ticks
    protected long startBreakingTime;
    // Seconds
    protected double timeNeededToBreak;
    // Ticks
    protected double stopBreakingTime;

    private static boolean isInvalidGameType(EntityPlayer player) {
        // Creative mode player can break blocks just like they are in
        // survival mode if "delayed block breaking" option is enabled
        // TODO: implement canBreak & canPlace feature
        return player.getGameType() == GameType.ADVENTURE ||
               player.getGameType() == GameType.SPECTATOR;
    }

    protected void handleMovement(EntityPlayer player, Vector3f newPos, Vector3f newRot) {
        var world = player.getLocation().dimension();
        ((AllayEntityPhysicsService) world.getEntityService().getPhysicsService()).offerClientMove(player, new Location3d(
                newPos.getX(), newPos.getY(), newPos.getZ(),
                newRot.getX(), newRot.getY(), newRot.getZ(),
                world
        ));
    }

    protected void handleBlockAction(EntityPlayer player, List<PlayerBlockActionData> blockActions, long time) {
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
            // Check interact distance
            switch (action.getAction()) {
                case START_BREAK, BLOCK_CONTINUE_DESTROY -> {
                    if (!player.canReachBlock(MathUtils.CBVecToJOMLVec(pos))) {
                        log.debug("Player {} tried to break a block out of reach", player.getOriginName());
                        continue;
                    }
                }
            }

            switch (action.getAction()) {
                case START_BREAK -> {
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace(), time);
                }
                case BLOCK_CONTINUE_DESTROY -> {
                    // When a player switches to breaking another block halfway through breaking one
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    // HACK: The client for some reason sends a meaningless BLOCK_CONTINUE_DESTROY before BLOCK_PREDICT_DESTROY, presumably a bug, so ignore it here
                    if (blockToBreakX == pos.getX() && blockToBreakY == pos.getY() && blockToBreakZ == pos.getZ()) {
                        continue;
                    }

                    stopBreak(player);
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace(), time);
                }
                case BLOCK_PREDICT_DESTROY -> {
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    completeBreak(player, pos.getX(), pos.getY(), pos.getZ());
                }
                case ABORT_BREAK -> {
                    // Digging interrupted
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    stopBreak(player);
                }
            }
        }
    }

    protected boolean isBreakingBlock() {
        return blockToBreak != null;
    }

    protected void startBreak(EntityPlayer player, int x, int y, int z, int blockFaceId, long startBreakingTime) {
        if (blockToBreak != null) {
            log.debug("Player {} tried to start breaking a block while already breaking one", player.getOriginName());
            stopBreak(player);
        }

        if (blockToBreakX == x && blockToBreakY == y && blockToBreakZ == z) {
            log.debug("Player {} tried to start breaking the same block twice", player.getOriginName());
            return;
        }

        blockToBreak = player.getDimension().getBlockState(x, y, z);
        if (blockToBreak.getBlockStateData().hardness() == -1) {
            log.debug("Player {} tried to break an unbreakable block", player.getOriginName());
            return;
        }

        blockToBreakX = x;
        blockToBreakY = y;
        blockToBreakZ = z;

        faceToBreak = blockFaceId;

        timeNeededToBreak = blockToBreak.getBlockType().getBlockBehavior().calculateBreakTime(blockToBreak, player.getItemInHand(), player);
        stopBreakingTime = startBreakingTime + timeNeededToBreak * 20.0d;

        var pk = new LevelEventPacket();
        pk.setType(BLOCK_START_BREAK);
        pk.setPosition(Vector3f.from(x, y, z));
        pk.setData(toNetworkBreakTime(timeNeededToBreak));
        player.getCurrentChunk().addChunkPacket(pk);
        sendBreakingPracticeAndTime(player, startBreakingTime);
    }

    protected int toNetworkBreakTime(double breakTime) {
        if (breakTime == 0) return 65535;
        return (int) (65535 / (timeNeededToBreak * 20));
    }

    protected void stopBreak(EntityPlayer player) {
        var pk = new LevelEventPacket();
        pk.setType(BLOCK_STOP_BREAK);
        pk.setPosition(Vector3f.from(blockToBreakX, blockToBreakY, blockToBreakZ));
        player.getCurrentChunk().addChunkPacket(pk);

        blockToBreakX = Integer.MAX_VALUE;
        blockToBreakY = Integer.MAX_VALUE;
        blockToBreakZ = Integer.MAX_VALUE;

        faceToBreak = 0;
        blockToBreak = null;

        startBreakingTime = 0;
        timeNeededToBreak = 0;
        stopBreakingTime = 0;
    }

    protected void completeBreak(EntityPlayer player, int x, int y, int z) {
        if (blockToBreakX != x || blockToBreakY != y || blockToBreakZ != z) {
            log.debug("Player {} tried to complete breaking a different block", player.getOriginName());
            return;
        }

        var currentTime = player.getWorld().getTick();
        if (Math.abs(currentTime - stopBreakingTime) <= BLOCK_BREAKING_TIME_FAULT_TOLERANCE) {
            var world = player.getDimension();
            var itemInHand = player.getItemInHand();
            world.breakBlock(blockToBreakX, blockToBreakY, blockToBreakZ, itemInHand, player);
            itemInHand.onBreakBlock(blockToBreak, player);
            if (itemInHand.isBroken()) {
                player.clearItemInHand();
            } else {
                player.notifyItemInHandChange();
            }
        } else {
            log.debug("Mismatch block breaking complete time! Expected: {}gt, actual: {}gt", stopBreakingTime, currentTime);
        }

        stopBreak(player);
    }

    protected void sendBreakingPracticeAndTime(EntityPlayer player, long currentTime) {
        updateBreakingTime(player, currentTime);

        var pk1 = new LevelEventPacket();
        var type = switch (Objects.requireNonNull(BlockFace.fromIndex(faceToBreak))) {
            case UP -> PARTICLE_BREAK_BLOCK_UP;
            case DOWN -> PARTICLE_BREAK_BLOCK_DOWN;
            case NORTH -> PARTICLE_BREAK_BLOCK_NORTH;
            case SOUTH -> PARTICLE_BREAK_BLOCK_SOUTH;
            case WEST -> PARTICLE_BREAK_BLOCK_WEST;
            case EAST -> PARTICLE_BREAK_BLOCK_EAST;
        };
        pk1.setType(type);
        pk1.setPosition(Vector3f.from(blockToBreakX + 0.5f, blockToBreakY + 0.5f, blockToBreakZ + 0.5f));
        pk1.setData(blockToBreak.blockStateHash());
        player.getCurrentChunk().addChunkPacket(pk1);

        var pk2 = new LevelEventPacket();
        pk2.setType(BLOCK_UPDATE_BREAK);
        pk2.setPosition(Vector3f.from(blockToBreakX, blockToBreakY, blockToBreakZ));
        pk2.setData(toNetworkBreakTime(timeNeededToBreak));

        player.getCurrentChunk().addChunkPacket(pk2);
    }

    protected void checkInteractDistance(EntityPlayer player) {
        if (!player.canReach(blockToBreakX + 0.5f, blockToBreakY + 0.5f, blockToBreakZ + 0.5f)) {
            log.debug("Player {} tried to interact with a block out of reach", player.getOriginName());
            stopBreak(player);
        }
    }

    protected void updateBreakingTime(EntityPlayer player, long currentTime) {
        var newBreakingTime = blockToBreak.getBehavior().calculateBreakTime(blockToBreak, player.getItemInHand(), player);
        if (timeNeededToBreak == newBreakingTime) {
            return;
        }

        // Breaking time has changed, make adjustments
        var timeLeft = stopBreakingTime - currentTime;
        stopBreakingTime = currentTime + timeLeft * (timeNeededToBreak / newBreakingTime);
        timeNeededToBreak = newBreakingTime;
    }

    protected void handleInputData(EntityPlayer player, Set<PlayerAuthInputData> inputData) {
        if (player.isDead()) {
            return;
        }

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
                case START_JUMPING ->
                        ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent()).onJump();
            }
        }
    }

    @Override
    public void handleSync(EntityPlayer player, PlayerAuthInputPacket packet, long receiveTime) {
        handleInputData(player, packet.getInputData());
        handleSingleItemStackRequest(player, packet.getItemStackRequest(), receiveTime);
    }

    @Override
    public PacketSignal handleAsync(EntityPlayer player, PlayerAuthInputPacket packet, long receiveTime) {
        if (notReadyForInput(player)) {
            return PacketSignal.HANDLED;
        }

        var baseComponent = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent());
        if (baseComponent.isAwaitingTeleportACK()) {
            var clientPos = MathUtils.CBVecToJOMLVec(packet.getPosition().sub(0, player.getNetworkOffset(), 0));
            var diff = baseComponent.getExpectedTeleportPos().sub(clientPos.x(), clientPos.y(), clientPos.z(), new org.joml.Vector3d()).length();
            if (diff > TELEPORT_ACK_DIFF_TOLERANCE) {
                // The player has moved before it received the teleport packet. Ignore this movement entirely and
                // wait for the client to sync itself back to the server. Once we get a movement that is close
                // enough to the teleport position, we'll allow the player to move around again.
                return PacketSignal.HANDLED;
            }
            baseComponent.ackTeleported();
        }

        if (!validateClientLocation(player, packet.getPosition(), packet.getRotation())) {
            // Ignore this auth packet if the pos provided by client is not valid
            return PacketSignal.HANDLED;
        }

        if (isLocationChanged(player, packet.getPosition(), packet.getRotation())) {
            // The pos which client sends to the server is higher than the actual coordinates (one base offset)
            handleMovement(player, packet.getPosition().sub(0, player.getNetworkOffset(), 0), packet.getRotation());
        }
        handleBlockAction(player, packet.getPlayerActions(), receiveTime);
        if (isBreakingBlock()) {
            sendBreakingPracticeAndTime(player, receiveTime);
            checkInteractDistance(player);
        }
        return PacketSignal.UNHANDLED;
    }

    protected boolean validateClientLocation(EntityPlayer player, Vector3f pos, Vector3f rot) {
        var valid = !Float.isNaN(pos.getX()) && !Float.isNaN(pos.getY()) && !Float.isNaN(pos.getZ()) &&
                    !Float.isNaN(rot.getX()) && !Float.isNaN(rot.getY()) && !Float.isNaN(rot.getZ());
        if (!valid) {
            // Sometimes, the PlayerAuthInput packet is in fact sent with NaN/INF after being teleported (to another
            // world). For this reason, we don't actually return an error if this happens, because this will result
            // in the player being kicked. Just log it and replace the NaN value with the one we have tracked server-side.
            log.debug("Found NaN in PlayerAuthInputPacket sent by player {}", player.getOriginName());
        }
        return valid;
    }

    protected boolean isLocationChanged(EntityPlayer player, Vector3f pos, Vector3f rot) {
        // The PlayerAuthInput packet is sent every tick, so don't do anything if the position and rotation were unchanged.
        var location = player.getLocation();
        return Double.compare(location.x(), pos.getX()) != 0 || Double.compare(location.y() + player.getNetworkOffset(), pos.getY()) != 0 || Double.compare(location.z(), pos.getZ()) != 0 ||
               Double.compare(location.pitch(), rot.getX()) != 0 || Double.compare(location.yaw(), rot.getY()) != 0 || Double.compare(location.headYaw(), rot.getZ()) != 0;
    }

    protected void handleSingleItemStackRequest(EntityPlayer player, ItemStackRequest request, long receiveTime) {
        // We had no idea why the client still use PlayerAuthInputPacket to hold ItemStackRequest
        // Instead of using ItemStackRequestPacket
        // This seems only happen when player break a block (MineBlockAction)
        if (request == null) {
            return;
        }

        var pk = new ItemStackRequestPacket();
        pk.getRequests().add(request);
        // Forward it to ItemStackRequestPacketProcessor
        Objects.requireNonNull(((EntityPlayerNetworkComponentImpl) ((EntityPlayerImpl) player).getPlayerNetworkComponent()).getPacketProcessorHolder().getProcessor(pk)).handleSync(player, pk, receiveTime);
    }

    protected boolean notReadyForInput(EntityPlayer player) {
        return !player.isInitialized() || !player.isSpawned() || player.willBeDespawnedNextTick();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_AUTH_INPUT;
    }
}
