package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.action.ContinueBreakAction;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.action.StartBreakAction;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.eventbus.event.player.PlayerJumpEvent;
import org.allaymc.api.eventbus.event.player.PlayerPunchAirEvent;
import org.allaymc.api.eventbus.event.player.PlayerPunchBlockEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.permission.Tristate;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.particle.PunchBlockParticle;
import org.allaymc.api.world.sound.AttackSound;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.world.physics.AllayEntityPhysicsEngine;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.joml.Vector3i;

import java.util.List;
import java.util.Set;

/**
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class PlayerAuthInputPacketProcessor extends PacketProcessor<PlayerAuthInputPacket> {

    // Minimum progress (0-1) required to allow client's BLOCK_PREDICT_DESTROY for vanilla blocks
    // Similar to Geyser's approach, we're tolerant to account for timing differences
    protected static final float BLOCK_BREAKING_PROGRESS_TOLERANCE = 0.65f;
    protected static final int TELEPORT_ACK_DIFF_TOLERANCE = 1;
    protected static final float PLAYER_NETWORK_OFFSET = 1.62f;

    protected int breakingPosX = Integer.MAX_VALUE;
    protected int breakingPosY = Integer.MAX_VALUE;
    protected int breakingPosZ = Integer.MAX_VALUE;

    protected BlockFace faceToBreak;
    protected BlockState blockToBreak;

    // Current breaking progress (0-1), where 1.0 means the block should be broken
    protected float currentProgress;
    // Progress added per tick
    protected float progressPerTick;
    // Whether we must break the block ourselves (for custom blocks)
    protected boolean serverSideBlockBreaking;

    private static boolean isInvalidGameType(Player player) {
        var entity = player.getControlledEntity();
        // Creative mode player can break blocks just like they are in
        // survival mode if "delayed block breaking" option is enabled
        // TODO: implement canBreak & canPlace feature
        return entity.getGameMode() == GameMode.ADVENTURE ||
               entity.getGameMode() == GameMode.SPECTATOR;
    }

    protected void handleMovement(Player player, Vector3f newPos, Vector3f newRot) {
        var entity = player.getControlledEntity();
        var world = entity.getLocation().dimension();
        ((AllayEntityPhysicsEngine) world.getEntityManager().getPhysicsService()).offerClientMove(entity, new Location3d(
                newPos.getX(), newPos.getY(), newPos.getZ(),
                newRot.getX(), newRot.getY(), world
        ));
    }

    protected void handleBlockAction(Player player, List<PlayerBlockActionData> blockActions) {
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
            // Check interact distance
            switch (action.getAction()) {
                case START_BREAK, BLOCK_CONTINUE_DESTROY -> {
                    if (!player.getControlledEntity().canReachBlock(NetworkHelper.fromNetwork(pos))) {
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

                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
                }
                case BLOCK_CONTINUE_DESTROY -> {
                    // When a player switches to breaking another block halfway through breaking one
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    // HACK: The client for some reason sends a meaningless BLOCK_CONTINUE_DESTROY before BLOCK_PREDICT_DESTROY, presumably a bug, so ignore it here
                    if (this.breakingPosX == pos.getX() && this.breakingPosY == pos.getY() && this.breakingPosZ == pos.getZ()) {
                        continue;
                    }

                    stopBreak(player);
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
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
        return this.blockToBreak != null;
    }

    protected void startBreak(Player player, int x, int y, int z, int blockFaceId) {
        var entity = player.getControlledEntity();
        var dimension = entity.getDimension();
        if (this.blockToBreak != null) {
            log.debug("Player {} tried to start breaking a block while already breaking one", player.getOriginName());
            stopBreak(player);
        }

        if (this.breakingPosX == x && this.breakingPosY == y && this.breakingPosZ == z) {
            log.debug("Player {} tried to start breaking the same block twice", player.getOriginName());
            return;
        }

        this.blockToBreak = dimension.getBlockState(x, y, z);
        this.faceToBreak = BlockFace.fromIndex(blockFaceId);
        if (this.faceToBreak == null) {
            log.debug("Player {} tried to break a block with an invalid face {}", player.getOriginName(), blockFaceId);
            this.blockToBreak = null;
            return;
        }

        // If there is fire in the face, try extinguishing it
        var sidePos = this.faceToBreak.offsetPos(x, y, z);
        var sideBlock = dimension.getBlockState(sidePos);
        if (sideBlock.getBlockType() == BlockTypes.FIRE || sideBlock.getBlockType() == BlockTypes.SOUL_FIRE) {
            var event = new BlockBreakEvent(new Block(sideBlock, new Position3i(sidePos, dimension)), entity.getItemInHand(), entity);
            if (!event.call()) {
                // Resend the block because on client side that was extinguished
                player.viewBlockUpdate(sidePos, 0, sideBlock);
            } else {
                dimension.setBlockState(sidePos, BlockTypes.AIR.getDefaultState());
                dimension.addSound(MathUtils.center(sidePos), SimpleSound.FIRE_EXTINGUISH);
            }
        }

        this.breakingPosX = x;
        this.breakingPosY = y;
        this.breakingPosZ = z;

        var block = new Block(blockToBreak, new Position3i(x, y, z, dimension));
        var event = new PlayerPunchBlockEvent(entity, block, faceToBreak);
        if (event.call()) {
            this.blockToBreak.getBlockType().getBlockBehavior().onPunch(block, faceToBreak, entity.getItemInHand(), entity);
        }

        double breakTimeSeconds;
        if (entity.getGameMode() != GameMode.CREATIVE) {
            breakTimeSeconds = this.blockToBreak.getBlockType().getBlockBehavior().calculateBreakTime(this.blockToBreak, entity.getItemInHand(), entity);
        } else {
            // Creative mode players can break blocks instantly
            breakTimeSeconds = 0;
        }

        // Check if this is a custom block - custom blocks require server-side breaking
        // because their "minecraft:destructible_by_mining" is set to a very large value
        this.serverSideBlockBreaking = ((AllayBlockType<?>) this.blockToBreak.getBlockType()).isCustomBlock();

        // Calculate progress per tick (1.0 = block fully broken)
        // If breakTimeSeconds is 0, it's instant break
        this.progressPerTick = breakTimeSeconds > 0 ? (float) (1.0 / (breakTimeSeconds * 20.0)) : 1.0f;
        this.currentProgress = this.progressPerTick; // First tick progress

        dimension.addBlockAction(x, y, z, new StartBreakAction(breakTimeSeconds));
        dimension.addParticle(
                this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f,
                new PunchBlockParticle(this.blockToBreak, this.faceToBreak)
        );
    }

    protected void stopBreak(Player player) {
        if (this.blockToBreak == null) {
            log.debug("Player {} tried to stop breaking a block while not breaking any block", player.getOriginName());
            return;
        }

        player.getControlledEntity().getDimension().addBlockAction(this.breakingPosX, this.breakingPosY, this.breakingPosZ, SimpleBlockAction.STOP_BREAK);
        this.breakingPosX = Integer.MAX_VALUE;
        this.breakingPosY = Integer.MAX_VALUE;
        this.breakingPosZ = Integer.MAX_VALUE;
        this.faceToBreak = null;
        this.blockToBreak = null;
        this.currentProgress = 0;
        this.progressPerTick = 0;
        this.serverSideBlockBreaking = false;
    }

    protected void completeBreak(Player player, int x, int y, int z) {
        if (this.breakingPosX != x || this.breakingPosY != y || this.breakingPosZ != z) {
            log.debug("Player {} tried to complete breaking a different block", player.getOriginName());
            return;
        }

        // Validate progress to prevent cheating (e.g., sending BLOCK_PREDICT_DESTROY too early)
        // Similar to Geyser's approach:
        // - Custom blocks require server-side breaking (progress >= 1.0), client won't send BLOCK_PREDICT_DESTROY
        // - Vanilla blocks can trust client prediction with some tolerance (progress >= 0.65)
        if (!mayBreak()) {
            log.debug("Player {} tried to break block too early! Progress: {}, serverSide: {}",
                    player.getOriginName(), this.currentProgress, this.serverSideBlockBreaking);
            return;
        }

        doBlockBreak(player);
        stopBreak(player);
    }

    /**
     * Checks if the block may be broken based on current progress.
     * Similar to Geyser's approach:
     * - Server-side breaking (custom blocks): requires progress >= 1.0
     * - Client prediction (vanilla blocks): allows progress >= 0.65 for tolerance
     */
    protected boolean mayBreak() {
        if (this.serverSideBlockBreaking) {
            // Custom blocks should be broken by server in handleAsync, not here
            // But if somehow we reach here, require full progress
            return this.currentProgress >= 1.0f;
        }
        // Vanilla blocks: be tolerant to account for timing differences
        return this.currentProgress >= BLOCK_BREAKING_PROGRESS_TOLERANCE;
    }

    protected void doBlockBreak(Player player) {
        var entity = player.getControlledEntity();
        var dimension = entity.getDimension();
        var itemInHand = entity.getItemInHand();
        if (dimension.breakBlock(this.breakingPosX, this.breakingPosY, this.breakingPosZ, itemInHand, entity)) {
            itemInHand.onBreakBlock(this.blockToBreak, entity);
            if (itemInHand.isBroken()) {
                entity.clearItemInHand();
            } else {
                entity.notifyItemInHandChange();
            }
        } else {
            // Failed to break the block (e.g. BlockBreakEvent cancelled), restore block state
            player.viewBlockUpdate(new Vector3i(this.breakingPosX, this.breakingPosY, this.breakingPosZ), 0, this.blockToBreak);
        }
    }

    protected boolean checkInteractDistance(Player player) {
        if (!player.getControlledEntity().canReach(this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f)) {
            log.debug("Player {} tried to interact with a block out of reach", player.getOriginName());
            stopBreak(player);
            return false;
        }

        return true;
    }

    protected void updateBreakingProgress(Player player) {
        var entity = player.getControlledEntity();
        var newBreakingTime = this.blockToBreak.getBehavior().calculateBreakTime(this.blockToBreak, entity.getItemInHand(), entity);

        // Recalculate progress per tick based on new breaking time
        float newProgressPerTick = newBreakingTime > 0 ? (float) (1.0 / (newBreakingTime * 20.0)) : 1.0f;

        // Accumulate progress for this tick
        this.currentProgress += newProgressPerTick;
        this.progressPerTick = newProgressPerTick;
    }

    protected void handleInputData(Player player, Set<PlayerAuthInputData> inputData) {
        var entity = player.getControlledEntity();
        if (entity.isDead()) {
            return;
        }

        for (var input : inputData) {
            switch (input) {
                case START_SPRINTING -> {
                    if (entity.getFoodLevel() <= 6) {
                        log.warn("Player {} tried to start sprinting without enough food level", player.getOriginName());
                        return;
                    }

                    entity.setSprinting(true);
                }
                case STOP_SPRINTING -> entity.setSprinting(false);
                case START_SNEAKING -> entity.setSneaking(true);
                case STOP_SNEAKING -> entity.setSneaking(false);
                case START_SWIMMING -> entity.setSwimming(true);
                case STOP_SWIMMING -> entity.setSwimming(false);
                case START_GLIDING -> entity.setGliding(true);
                case STOP_GLIDING -> entity.setGliding(false);
                case START_CRAWLING -> entity.setCrawling(true);
                case STOP_CRAWLING -> entity.setCrawling(false);
                case START_SPIN_ATTACK -> entity.setSpinAttacking(true);
                case STOP_SPIN_ATTACK -> entity.setSpinAttacking(false);
                case START_JUMPING -> {
                    new PlayerJumpEvent(entity).call();
                    entity.exhaust(entity.isSprinting() ? 0.2f : 0.05f);
                }
                case START_FLYING -> {
                    var gameMode = entity.getGameMode();
                    var tristate = entity.hasPermission(Permissions.ABILITY_FLY);
                    if (tristate == Tristate.FALSE ||
                        (gameMode != GameMode.CREATIVE && gameMode != GameMode.SPECTATOR && !tristate.asBoolean())) {

                        // Reset client-side flying state
                        var controller = entity.getController();
                        controller.viewPlayerPermission(controller);

                        log.warn("Player {} tried to start flying without permission", player.getOriginName());
                        return;
                    }

                    entity.setFlying(true);
                }
                case STOP_FLYING -> entity.setFlying(false);
                case MISSED_SWING -> {
                    var event = new PlayerPunchAirEvent(entity);
                    if (event.call()) {
                        entity.getDimension().addSound(entity.getLocation(), new AttackSound(false));
                    }
                }
            }
        }
    }

    @Override
    public void handleSync(Player player, PlayerAuthInputPacket packet, long receiveTime) {
        handleInputData(player, packet.getInputData());
        handleSingleItemStackRequest(player, packet.getItemStackRequest(), receiveTime);
    }

    @Override
    public PacketSignal handleAsync(Player player, PlayerAuthInputPacket packet, long receiveTime) {
        if (notReadyForInput(player)) {
            return PacketSignal.HANDLED;
        }

        var baseComponent = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player.getControlledEntity()).getBaseComponent());
        if (baseComponent.getExpectedTeleportPos() != null) {
            var clientPos = NetworkHelper.fromNetwork(packet.getPosition().sub(0, PLAYER_NETWORK_OFFSET, 0));
            var diff = baseComponent.getExpectedTeleportPos().sub(clientPos.x(), clientPos.y(), clientPos.z(), new org.joml.Vector3d()).length();
            if (diff > TELEPORT_ACK_DIFF_TOLERANCE) {
                // The player has moved before it received the teleport packet. Ignore this movement entirely and
                // wait for the client to sync itself back to the server. Once we get a movement that is close
                // enough to the teleport position, we'll allow the player to move around again.
                return PacketSignal.HANDLED;
            }
            baseComponent.setExpectedTeleportPos(null);
        }

        if (!validateClientLocation(player, packet.getPosition(), packet.getRotation())) {
            // Ignore this auth packet if the pos provided by the client is not valid
            return PacketSignal.HANDLED;
        }

        if (isLocationChanged(player, packet.getPosition(), packet.getRotation())) {
            // The pos which the client sends to the server is higher than the actual coordinates (one base offset)
            handleMovement(player, packet.getPosition().sub(0, PLAYER_NETWORK_OFFSET, 0), packet.getRotation());
        }
        handleBlockAction(player, packet.getPlayerActions());
        if (isBreakingBlock() && checkInteractDistance(player)) {
            // Update breaking progress
            updateBreakingProgress(player);

            // Check if progress has reached 100% - server authoritative block breaking.
            // For custom blocks: their "minecraft:destructible_by_mining" is set to a very large value,
            //   so the client will never send BLOCK_PREDICT_DESTROY. Server must proactively break.
            // For vanilla blocks: client may send BLOCK_PREDICT_DESTROY at ~65% progress (handled in
            //   completeBreak), but if not received, server will break at 100% as fallback.
            if (this.currentProgress >= 1.0f) {
                doBlockBreak(player);
                stopBreak(player);
            } else {
                // Still breaking, update particles and send progress to client
                var entity = player.getControlledEntity();
                var dimension = entity.getDimension();
                dimension.addParticle(
                        this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f,
                        new PunchBlockParticle(this.blockToBreak, this.faceToBreak)
                );
                // Calculate break time in seconds for ContinueBreakAction
                double breakTimeSeconds = this.progressPerTick > 0 ? 1.0 / (this.progressPerTick * 20.0) : 0;
                dimension.addBlockAction(
                        this.breakingPosX, this.breakingPosY, this.breakingPosZ,
                        new ContinueBreakAction(breakTimeSeconds)
                );
            }
        }
        return PacketSignal.UNHANDLED;
    }

    protected boolean validateClientLocation(Player player, Vector3f pos, Vector3f rot) {
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

    protected boolean isLocationChanged(Player player, Vector3f pos, Vector3f rot) {
        // The PlayerAuthInput packet is sent every tick, so don't do anything if the position and rotation were unchanged.
        var entity = player.getControlledEntity();
        var location = entity.getLocation();
        // If the entity is immobile, ignore the pos changes directly
        var posChanged = !entity.isImmobile() &&
                         (Double.compare(location.x(), pos.getX()) != 0 ||
                          Double.compare(location.y() + PLAYER_NETWORK_OFFSET, pos.getY()) != 0 ||
                          Double.compare(location.z(), pos.getZ()) != 0);

        return posChanged || Double.compare(location.pitch(), rot.getX()) != 0 || Double.compare(location.yaw(), rot.getY()) != 0;
    }

    protected void handleSingleItemStackRequest(Player player, ItemStackRequest request, long receiveTime) {
        // We had no idea why the client still uses PlayerAuthInputPacket to hold ItemStackRequest
        // Instead of using ItemStackRequestPacket
        // This seems to only happen when player breaks a block (MineBlockAction)
        if (request == null) {
            return;
        }

        var pk = new ItemStackRequestPacket();
        pk.getRequests().add(request);
        // Forward it to ItemStackRequestPacketProcessor
        ((AllayPlayer) player).handlePacketSync(pk, receiveTime);
    }

    protected boolean notReadyForInput(Player player) {
        var entity = player.getControlledEntity();
        return !player.isInitialized() || entity == null || !entity.isSpawned() || entity.willBeDespawnedNextTick();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_AUTH_INPUT;
    }
}
