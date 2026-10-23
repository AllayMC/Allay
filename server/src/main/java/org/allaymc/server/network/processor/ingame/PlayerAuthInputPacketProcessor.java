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
import org.allaymc.api.eventbus.event.server.PlayerControlModeUpdateEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.*;
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
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.joml.Vector3d;
import org.joml.Vector3i;

import java.util.List;
import java.util.Set;

/**
 * {@code PlayerAuthInputPacketProcessor} admits player movement and block-breaking
 * input on the receive path, then leaves ordinary input flags and embedded
 * inventory requests for synchronous handling. A pending teleport rejects a
 * reported feet distance above the acknowledgement tolerance before coordinate
 * validation; a {@code NaN} distance does not trigger that comparison. Accepted
 * positions are offered to the physics engine rather than
 * assigned directly, and the location validator rejects {@code NaN} components
 * without rejecting infinity or substituting server coordinates.
 *
 * <p>
 * The processor retains one block-breaking position, face, captured block state,
 * and accumulated progress across admitted packets. Vanilla blocks permit a
 * tolerant predicted completion, while custom blocks require full progress
 * because their protocol definition advertises a maximum mining duration.
 * {@code startBreak} seeds one progress increment, and admitted input packets add
 * further increments using a twenty-tick conversion rather than elapsed receive
 * time. Rejected predictions resend both block
 * layers; stopping clears tracking only after its stop action succeeds. These
 * mutable fields have no independent locking, so this processor does not provide
 * concurrent admission guarantees.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class PlayerAuthInputPacketProcessor extends PacketProcessor<PlayerAuthInputPacket> {

    /**
     * {@code BLOCK_BREAKING_PROGRESS_TOLERANCE} permits vanilla predicted
     * destruction at sixty-five percent progress, before full server completion.
     */
    protected static final float BLOCK_BREAKING_PROGRESS_TOLERANCE = 0.65f;
    /**
     * {@code TELEPORT_ACK_DIFF_TOLERANCE} accepts reported feet positions within
     * one block of the expected teleport destination before movement resumes.
     */
    protected static final int TELEPORT_ACK_DIFF_TOLERANCE = 1;
    /**
     * {@code PLAYER_NETWORK_OFFSET} converts the client's elevated position to
     * feet coordinates by subtracting 1.62 blocks from its vertical component.
     */
    protected static final float PLAYER_NETWORK_OFFSET = 1.62f;

    // breakingPosX, breakingPosY, and breakingPosZ identify the tracked block.
    // stopBreak resets all three to Integer.MAX_VALUE after publishing STOP_BREAK;
    // blockToBreak determines whether tracking is active.
    /** Tracked block X coordinate. */
    protected int breakingPosX = Integer.MAX_VALUE;
    /** Tracked block Y coordinate. */
    protected int breakingPosY = Integer.MAX_VALUE;
    /** Tracked block Z coordinate. */
    protected int breakingPosZ = Integer.MAX_VALUE;

    /**
     * {@code faceToBreak} holds the validated face used for punch feedback.
     * {@code stopBreak} clears it when active tracking ends.
     */
    protected BlockFace faceToBreak;
    /**
     * {@code blockToBreak} holds the state captured by {@code startBreak}.
     * A {@code null} value makes {@code isBreakingBlock} report inactive tracking.
     */
    protected BlockState blockToBreak;

    /**
     * {@code currentProgress} accumulates break fractions per admitted packet.
     * Values at least one cause server completion regardless of block kind.
     */
    protected float currentProgress;
    /**
     * {@code progressPerTick} stores the latest twenty-tick-derived progress
     * increment, which also determines the continuation action's displayed duration.
     */
    protected float progressPerTick;
    /**
     * {@code serverSideBlockBreaking} marks custom blocks requiring full progress.
     * {@code mayBreak} otherwise allows the vanilla predicted-completion tolerance.
     */
    protected boolean serverSideBlockBreaking;

    /**
     * Returns whether the current game mode forbids these block-breaking actions.
     * Adventure and spectator modes are rejected; creative mode remains eligible
     * for the same action path as survival mode.
     */
    private static boolean isInvalidGameType(Player player) {
        var entity = player.getControlledEntity();
        // TODO: Honor item-specific adventure breaking and placement restrictions
        // before allowing those operations; this check rejects adventure mode entirely.
        return entity.getGameMode() == GameMode.ADVENTURE ||
               entity.getGameMode() == GameMode.SPECTATOR;
    }

    /**
     * Offers a feet-coordinate movement to the current dimension's physics engine.
     * The engine owns later movement processing and can ignore unregistered or
     * unchanged entities; this helper does not move the entity immediately.
     */
    protected void handleMovement(Player player, Vector3f newPos, Vector3f newRot) {
        var entity = player.getControlledEntity();
        var world = entity.getLocation().dimension();
        ((AllayEntityPhysicsEngine) world.getEntityManager().getPhysicsService()).offerClientMove(entity, new Location3d(
                newPos.getX(), newPos.getY(), newPos.getZ(),
                newRot.getX(), newRot.getY(), world
        ));
    }

    /**
     * Processes supplied block actions in list order against this connection's
     * current breaking state. Start and continue actions require block reach,
     * while game-mode and break permissions also gate predicted completion.
     *
     * <p>
     * Continuing the already tracked position leaves its progress intact;
     * continuing another position stops the old break before starting the new one.
     * A forbidden predicted destroy resends layer zero immediately, whereas an
     * allowed prediction delegates progress checks and two-layer recovery to
     * {@code completeBreak}. An abort in a forbidden game mode leaves existing
     * tracking unchanged rather than clearing it.
     */
    protected void handleBlockAction(Player player, List<PlayerBlockActionData> blockActions) {
        for (var action : blockActions) {
            var pos = action.getBlockPosition();
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
                    if (isInvalidGameType(player) || !player.canBreakBlocks()) {
                        continue;
                    }

                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
                }
                case BLOCK_CONTINUE_DESTROY -> {
                    if (isInvalidGameType(player) || !player.canBreakBlocks()) {
                        continue;
                    }

                    // Repeated continuation for the active position must not
                    // restart progress before a following prediction; only a
                    // changed position selects a new breaking target.
                    if (this.breakingPosX == pos.getX() && this.breakingPosY == pos.getY() && this.breakingPosZ == pos.getZ()) {
                        continue;
                    }

                    stopBreak(player);
                    startBreak(player, pos.getX(), pos.getY(), pos.getZ(), action.getFace());
                }
                case BLOCK_PREDICT_DESTROY -> {
                    if (isInvalidGameType(player) || !player.canBreakBlocks()) {
                        // A forbidden prediction may already have hidden the
                        // block on the client; resend layer zero without
                        // admitting or completing the requested break.
                        var state = player.getControlledEntity().getLocation().dimension().getBlockState(new Vector3d(pos.getX(), pos.getY(), pos.getZ()));
                        player.viewBlockUpdate(new Vector3i(pos.getX(), pos.getY(), pos.getZ()), 0, state);
                        continue;
                    }

                    completeBreak(player, pos.getX(), pos.getY(), pos.getZ());
                }
                case ABORT_BREAK -> {
                    if (isInvalidGameType(player)) {
                        continue;
                    }

                    stopBreak(player);
                }
            }
        }
    }

    /**
     * Returns whether a captured block state marks active breaking work.
     */
    protected boolean isBreakingBlock() {
        return this.blockToBreak != null;
    }

    /**
     * Starts tracking a block after stopping any previously active break.
     * An invalid face abandons the newly captured state, and callers perform
     * permission and reach checks before invoking this helper.
     *
     * <p>
     * Adjacent fire gets its own cancellable break event before punch handling.
     * Cancelling the punch event suppresses the block's punch callback but does
     * not cancel tracking or progress initialization. Custom-block identity
     * selects full-progress completion, and nonpositive break durations initialize
     * an immediate full increment. Start feedback follows those state updates,
     * with no rollback of earlier fire changes or tracking if a callback fails.
     */
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

        // Fire occupies the clicked face independently of the target block;
        // offer its removal separately before initializing feedback for the
        // block being broken.
        var sidePos = this.faceToBreak.offsetPos(x, y, z);
        var sideBlock = dimension.getBlockState(sidePos);
        if (sideBlock.getBlockType() == BlockTypes.FIRE || sideBlock.getBlockType() == BlockTypes.SOUL_FIRE) {
            var event = new BlockBreakEvent(new Block(sideBlock, new Position3i(sidePos, dimension)), entity.getItemInHand(), entity);
            if (!event.call()) {
                // The client can already predict extinguishing this adjacent
                // fire; event rejection resends its server state without
                // cancelling tracking for the original target block.
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
            // Creative mode bypasses the calculated survival mining duration.
            breakTimeSeconds = 0;
        }

        // Protocol.encodeCustomBlockDefinition advertises Float.MAX_VALUE mining
        // duration for custom blocks, so server progress must supply completion
        // instead of depending on a timely client prediction.
        this.serverSideBlockBreaking = ((AllayBlockType<?>) this.blockToBreak.getBlockType()).isCustomBlock();

        // Convert seconds to twenty nominal ticks; nonpositive durations use
        // full progress immediately instead of dividing by zero or a negative value.
        this.progressPerTick = breakTimeSeconds > 0 ? (float) (1.0 / (breakTimeSeconds * 20.0)) : 1.0f;
        this.currentProgress = this.progressPerTick; // initial admitted-packet progress

        dimension.addBlockAction(x, y, z, new StartBreakAction(breakTimeSeconds));
        dimension.addParticle(
                this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f,
                new PunchBlockParticle(this.blockToBreak, this.faceToBreak)
        );
    }

    /**
     * Publishes stop feedback and clears the active target and progress fields.
     * An inactive call only logs; if feedback publication throws, the clearing
     * assignments are not reached and previous tracking remains.
     */
    protected void stopBreak(Player player) {
        if (this.blockToBreak == null) {
            log.debug("Player {} tried to stop breaking a block while not breaking any block", player.getOriginName());
            return;
        }

        // Publish the stop while the old coordinates still identify its target;
        // only a successful publication reaches the tracking reset that follows.
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

    /**
     * Completes a prediction only for the currently tracked block coordinates.
     * A different position is ignored, while insufficient progress resends both
     * server block layers and leaves the active break available for later progress.
     *
     * <p>
     * Permitted completion invokes {@code doBlockBreak} before {@code stopBreak}.
     * A declined world break therefore restores the client's view and then stops
     * tracking, whereas a thrown break or item callback skips the later stop.
     * This helper does not independently recheck permissions or reach.
     */
    protected void completeBreak(Player player, int x, int y, int z) {
        if (this.breakingPosX != x || this.breakingPosY != y || this.breakingPosZ != z) {
            log.debug("Player {} tried to complete breaking a different block", player.getOriginName());
            return;
        }

        // Use the tracked block kind and progress together; vanilla predictions
        // have timing tolerance, while custom blocks require the same full
        // progress used by server completion.
        if (!mayBreak()) {
            log.debug("Player {} tried to break block too early! Progress: {}, serverSide: {}",
                    player.getOriginName(), this.currentProgress, this.serverSideBlockBreaking);
            // Prediction can hide both the block and an extra water layer;
            // restore both current server layers while retaining this unfinished
            // break for later progress.
            revertClientBlockPrediction(player, x, y, z);
            return;
        }

        // Finish world and item callbacks before clearing tracking so a thrown
        // callback leaves the target metadata available rather than hiding
        // incomplete breaking cleanup.
        doBlockBreak(player);
        stopBreak(player);
    }

    /**
     * Returns whether progress permits completion for the tracked block kind.
     * Custom blocks require a full fraction of one; other blocks permit the
     * sixty-five percent prediction tolerance without changing tracking state.
     *
     * <p>
     * {@code completeBreak} uses this distinction for client predictions, which
     * must not gain the vanilla early-completion allowance for custom blocks.
     * {@code handleAsync} separately completes every block at full progress
     * without invoking this predicate. A permitted result alone does not validate
     * target coordinates, permissions, or reach.
     */
    protected boolean mayBreak() {
        if (this.serverSideBlockBreaking) {
            // A custom-block prediction uses the same full-progress requirement
            // as server completion, preventing the early vanilla threshold from
            // admitting a custom block before its server work completes.
            return this.currentProgress >= 1.0f;
        }
        // Vanilla predictions can complete before the server's full-progress fallback.
        return this.currentProgress >= BLOCK_BREAKING_PROGRESS_TOLERANCE;
    }

    /**
     * Attempts the tracked world break and updates the held item after success.
     * A declined break resends both server block layers; a successful break runs
     * item wear before clearing or notifying the hand, without compensating the
     * world change if a later item callback fails.
     *
     * <p>
     * Item wear receives the captured {@code blockToBreak}, whereas recovery reads
     * the dimension's current states at the tracked coordinates. Those states can
     * differ after intervening world changes. This helper leaves tracking cleanup
     * to its caller after the operation returns normally.
     */
    protected void doBlockBreak(Player player) {
        var entity = player.getControlledEntity();
        var dimension = entity.getDimension();
        var itemInHand = entity.getItemInHand();
        // Apply tool wear only after the world accepts the break, so a
        // cancelled world operation does not consume durability through this
        // successful-break item callback.
        if (dimension.breakBlock(this.breakingPosX, this.breakingPosY, this.breakingPosZ, itemInHand, entity)) {
            itemInHand.onBreakBlock(this.blockToBreak, entity);
            if (itemInHand.isBroken()) {
                entity.clearItemInHand();
            } else {
                entity.notifyItemInHandChange();
            }
        } else {
            // A rejected world break leaves the server block authoritative;
            // resend both layers because client prediction may already have
            // removed the visible block and its water layer.
            revertClientBlockPrediction(player, this.breakingPosX, this.breakingPosY, this.breakingPosZ);
        }
    }

    /**
     * Resends both current block layers to correct a predicted client destroy.
     * Layer zero contains the main block and layer one can contain water; the
     * method sends them separately without modifying server blocks or tracking state.
     */
    protected void revertClientBlockPrediction(Player player, int x, int y, int z) {
        var dimension = player.getControlledEntity().getDimension();
        var pos = new Vector3i(x, y, z);
        player.viewBlockUpdate(pos, 0, dimension.getBlockState(x, y, z, 0));
        player.viewBlockUpdate(pos, 1, dimension.getBlockState(x, y, z, 1));
    }

    /**
     * Returns whether the controlled entity can reach the tracked block's center.
     * An out-of-reach target invokes {@code stopBreak} before returning {@code false},
     * so the caller must not continue progress work after rejection.
     */
    protected boolean checkInteractDistance(Player player) {
        if (!player.getControlledEntity().canReach(this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f)) {
            log.debug("Player {} tried to interact with a block out of reach", player.getOriginName());
            stopBreak(player);
            return false;
        }

        return true;
    }

    /**
     * Adds one increment using the captured block state and current held item.
     * Each invocation recomputes the twenty-tick-derived increment, so item or
     * player changes affect future additions while accumulated progress remains.
     *
     * <p>
     * A nonpositive duration supplies a full increment, and a positive duration
     * divides one by its nominal tick count. The method does not measure elapsed
     * receive time or cap the total at one. The stored {@code progressPerTick}
     * also supplies the duration used by subsequent continuation feedback.
     */
    protected void updateBreakingProgress(Player player) {
        var entity = player.getControlledEntity();
        var newBreakingTime = this.blockToBreak.getBehavior().calculateBreakTime(this.blockToBreak, entity.getItemInHand(), entity);

        // Recompute from current tool and player conditions without discarding
        // accumulated progress; twenty nominal ticks convert the duration to
        // one admitted-packet increment.
        float newProgressPerTick = newBreakingTime > 0 ? (float) (1.0 / (newBreakingTime * 20.0)) : 1.0f;

        this.currentProgress += newProgressPerTick;
        this.progressPerTick = newProgressPerTick;
    }

    /**
     * Applies supported input flags to a living controlled entity in set iteration
     * order. Dead entities are ignored; rejected sprint or flight starts return
     * from the whole method, leaving later flags in that iteration unprocessed.
     * Jump events do not gate exhaustion, and required-flight stops resend abilities.
     */
    protected void handleInputData(Player player, Set<PlayerAuthInputData> inputData) {
        var entity = player.getControlledEntity();
        if (entity.isDead()) {
            return;
        }

        for (var input : inputData) {
            switch (input) {
                case START_SPRINTING -> {
                    // Sprinting requires more than six food points.
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
                    // Sprinting jumps consume four times the ordinary jump exhaustion.
                    entity.exhaust(entity.isSprinting() ? 0.2f : 0.05f);
                }
                case START_FLYING -> {
                    if (!player.canFly()) {
                        // A forbidden flight start can already be predicted
                        // locally; resend the controller's ability state before
                        // abandoning the remaining input flags in this packet.
                        var controller = entity.getController();
                        controller.viewPlayerAbilities(controller);

                        log.warn("Player {} tried to start flying without permission", player.getOriginName());
                        return;
                    }

                    entity.setFlying(true);
                }
                case STOP_FLYING -> {
                    // Required flight overrides the client's stop request;
                    // resend abilities so its local state can retain the same
                    // restriction as the controlled entity.
                    entity.setFlying(player.isAlwaysFlying());
                    if (player.isAlwaysFlying()) {
                        entity.setFlying(true);
                        player.viewPlayerAbilities(player);
                    } else {
                        entity.setFlying(false);
                    }
                }
                case MISSED_SWING -> {
                    var event = new PlayerPunchAirEvent(entity);
                    if (event.call()) {
                        entity.getDimension().addSound(entity.getLocation(), new AttackSound(false));
                    }
                }
            }
        }
    }

    /**
     * Applies deferred input flags before forwarding any embedded item-stack
     * request. A failure in input handling prevents request forwarding, and the
     * method does not repeat receive-path readiness or teleport checks.
     */
    @Override
    public void handleSync(Player player, PlayerAuthInputPacket packet, long receiveTime) {
        handleInputData(player, packet.getInputData());
        handleSingleItemStackRequest(player, packet.getItemStackRequest(), receiveTime);
    }

    /**
     * Admits movement and breaking work for an initialized, spawned player.
     * Unready input is consumed immediately; ready input updates control-mode
     * observations before checking a pending teleport or validating coordinates.
     *
     * <p>
     * A position farther than the teleport tolerance consumes the whole packet
     * and retains the expected destination. A sufficiently close comparison
     * clears that destination before {@code NaN} validation, so a later validation
     * failure does not restore it. Accepted changed locations are offered to
     * physics, then block actions and one progress increment run on this receive
     * path. A newly started break already has its seeded increment, so this update
     * adds a second increment in that packet. Full progress invokes the world
     * break even without a client prediction;
     * unfinished progress emits feedback. Returning {@code UNHANDLED} permits the
     * synchronous flags and embedded request phase only after these steps finish.
     */
    @Override
    public PacketSignal handleAsync(Player player, PlayerAuthInputPacket packet, long receiveTime) {
        if (notReadyForInput(player)) {
            return PacketSignal.HANDLED;
        }

        updatePlayerInputState((AllayPlayer) player, packet);

        var baseComponent = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player.getControlledEntity()).getBaseComponent());
        if (baseComponent.getExpectedTeleportPos() != null) {
            var clientPos = NetworkHelper.fromNetwork(packet.getPosition().sub(0, PLAYER_NETWORK_OFFSET, 0));
            var diff = baseComponent.getExpectedTeleportPos().sub(clientPos.x(), clientPos.y(), clientPos.z(), new org.joml.Vector3d()).length();
            if (diff > TELEPORT_ACK_DIFF_TOLERANCE) {
                // Keep the expected destination until a reported feet position
                // is close enough; consuming this packet also defers its block
                // actions and synchronous input work.
                return PacketSignal.HANDLED;
            }
            // Clear acknowledgement before coordinate validation; a NaN
            // distance does not satisfy the greater-than rejection and can
            // reach this assignment before validation consumes the packet.
            baseComponent.setExpectedTeleportPos(null);
        }

        if (!validateClientLocation(player, packet.getPosition(), packet.getRotation())) {
            // Invalid coordinates consume the entire packet, preventing its
            // movement, block actions, input flags, and embedded inventory
            // request from reaching their later phases.
            return PacketSignal.HANDLED;
        }

        if (isLocationChanged(player, packet.getPosition(), packet.getRotation())) {
            // The client reports an elevated vertical position; subtract the
            // network offset so physics receives the entity's feet coordinates
            // in the current dimension.
            handleMovement(player, packet.getPosition().sub(0, PLAYER_NETWORK_OFFSET, 0), packet.getRotation());
        }
        handleBlockAction(player, packet.getPlayerActions());
        if (isBreakingBlock() && checkInteractDistance(player)) {
            updateBreakingProgress(player);

            // Full progress supplies custom-block completion despite its
            // advertised maximum mining duration, and also completes vanilla
            // breaking when no earlier tolerated prediction arrives.
            if (this.currentProgress >= 1.0f) {
                doBlockBreak(player);
                stopBreak(player);
            } else {
                var entity = player.getControlledEntity();
                var dimension = entity.getDimension();
                dimension.addParticle(
                        this.breakingPosX + 0.5f, this.breakingPosY + 0.5f, this.breakingPosZ + 0.5f,
                        new PunchBlockParticle(this.blockToBreak, this.faceToBreak)
                );
                // ContinueBreakAction expects seconds, so invert the nominal
                // twenty-tick progress rate used by the client-facing break feedback.
                double breakTimeSeconds = this.progressPerTick > 0 ? 1.0 / (this.progressPerTick * 20.0) : 0;
                dimension.addBlockAction(
                        this.breakingPosX, this.breakingPosY, this.breakingPosZ,
                        new ContinueBreakAction(breakTimeSeconds)
                );
            }
        }
        return PacketSignal.UNHANDLED;
    }

    /**
     * Returns whether position and rotation contain no {@code NaN} components.
     * Invalid values are logged and left unchanged; infinity passes this check,
     * and the caller decides to consume a packet returning {@code false}.
     */
    protected boolean validateClientLocation(Player player, Vector3f pos, Vector3f rot) {
        var valid = !Float.isNaN(pos.getX()) && !Float.isNaN(pos.getY()) && !Float.isNaN(pos.getZ()) &&
                    !Float.isNaN(rot.getX()) && !Float.isNaN(rot.getY()) && !Float.isNaN(rot.getZ());
        if (!valid) {
            // Log invalid components without throwing or replacing them; the
            // receive handler consumes the packet and leaves the server's
            // current position unchanged by this validation path.
            log.debug("Found NaN in PlayerAuthInputPacket sent by player {}", player.getOriginName());
        }
        return valid;
    }

    /**
     * Returns whether position or pitch and yaw differ from the current entity.
     * Immobility suppresses positional differences only; rotation can still
     * trigger movement offering, and comparison accounts for the network height offset.
     *
     * <p>
     * Only pitch and yaw participate in rotation comparison. A rotation change
     * returns {@code true} even for an immobile entity, and the caller then offers
     * the complete supplied position to physics rather than replacing it with
     * the old position. The physics engine's own movement rules therefore govern
     * how that offered position is eventually applied.
     */
    protected boolean isLocationChanged(Player player, Vector3f pos, Vector3f rot) {
        var entity = player.getControlledEntity();
        var location = entity.getLocation();
        // Compare server feet height with the client's elevated height while
        // allowing immobile entities to report rotation without treating position
        // changes alone as a new move.
        var posChanged = !entity.isImmobile() &&
                         (Double.compare(location.x(), pos.getX()) != 0 ||
                          Double.compare(location.y() + PLAYER_NETWORK_OFFSET, pos.getY()) != 0 ||
                          Double.compare(location.z(), pos.getZ()) != 0);

        return posChanged || Double.compare(location.pitch(), rot.getX()) != 0 || Double.compare(location.yaw(), rot.getY()) != 0;
    }

    /**
     * Forwards an embedded request through ordinary synchronous packet dispatch.
     * A {@code null} request has no effect; a present request is retained in a new
     * packet and uses the processor selected for the player's current state.
     */
    protected void handleSingleItemStackRequest(Player player, ItemStackRequest request, long receiveTime) {
        if (request == null) {
            return;
        }

        var pk = new ItemStackRequestPacket();
        pk.getRequests().add(request);
        // Embedded inventory work uses the ordinary packet processor so action
        // ordering and response rules remain shared with standalone requests,
        // while retaining this packet's receive tick.
        ((AllayPlayer) player).handlePacketSync(pk, receiveTime);
    }

    /**
     * Maps the packet's control modes and publishes an event after changed fields
     * are assigned. Protocol {@code COUNT} sentinels preserve the corresponding
     * previous input mode or interaction model, and unchanged observations emit
     * no event. An event failure does not roll back the assigned modes.
     *
     * <p>
     * The previous values are read before conversion so sentinel fields can
     * retain their observations while other fields still change. Listeners receive
     * both sets of values after all assignments; edits to the event payload do
     * not replace the already stored player observations.
     */
    protected void updatePlayerInputState(AllayPlayer player, PlayerAuthInputPacket packet) {
        var oldInputMode = player.getInputMode();
        var newInputMode = switch (packet.getInputMode()) {
            case UNDEFINED -> InputMode.UNDEFINED;
            case MOUSE -> InputMode.MOUSE;
            case TOUCH -> InputMode.TOUCH;
            case GAMEPAD -> InputMode.GAMEPAD;
            case MOTION_CONTROLLER -> InputMode.MOTION_CONTROLLER;
            // COUNT terminates the protocol enum rather than naming a usable
            // client input mode; preserve the previous observation instead of
            // publishing that sentinel as a control change.
            case COUNT -> oldInputMode;
        };

        var newPlayMode = switch (packet.getPlayMode()) {
            case NORMAL -> ClientPlayMode.NORMAL;
            case TEASER -> ClientPlayMode.TEASER;
            case SCREEN -> ClientPlayMode.SCREEN;
            case VIEWER -> ClientPlayMode.VIEWER;
            case REALITY -> ClientPlayMode.REALITY;
            case PLACEMENT -> ClientPlayMode.PLACEMENT;
            case LIVING_ROOM -> ClientPlayMode.LIVING_ROOM;
            case EXIT_LEVEL -> ClientPlayMode.EXIT_LEVEL;
            case EXIT_LEVEL_LIVING_ROOM -> ClientPlayMode.EXIT_LEVEL_LIVING_ROOM;
        };
        var oldPlayMode = player.getPlayMode();

        var oldInteractionModel = player.getInputInteractionModel();
        var newInteractionModel = switch (packet.getInputInteractionModel()) {
            case TOUCH -> InputInteractionModel.TOUCH;
            case CROSSHAIR -> InputInteractionModel.CROSSHAIR;
            case CLASSIC -> InputInteractionModel.CLASSIC;
            // COUNT terminates the protocol enum rather than identifying an
            // interaction model; retaining the old model prevents a sentinel
            // from becoming a reported control change.
            case COUNT -> oldInteractionModel;
        };

        var changed = newInputMode != oldInputMode ||
                      newPlayMode != oldPlayMode ||
                      newInteractionModel != oldInteractionModel;

        if (changed) {
            // Publish all three observations before notifying listeners so the
            // event's new values agree with player accessors even when a
            // listener inspects them or throws.
            player.setInputMode(newInputMode);
            player.setPlayMode(newPlayMode);
            player.setInputInteractionModel(newInteractionModel);

            new PlayerControlModeUpdateEvent(
                    player,
                    oldInputMode,
                    newInputMode,
                    oldPlayMode,
                    newPlayMode,
                    oldInteractionModel,
                    newInteractionModel
            ).call();
        }
    }

    /**
     * Returns whether initialization or entity lifecycle prevents input admission.
     * A missing, unspawned, or pending-despawn controlled entity consumes incoming
     * input through the caller rather than queuing it for a later lifecycle state.
     */
    protected boolean notReadyForInput(Player player) {
        var entity = player.getControlledEntity();
        return !player.isInitialized() || entity == null || !entity.isSpawned() || entity.willBeDespawnedLater();
    }

    /**
     * Returns the player authoritative input packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_AUTH_INPUT;
    }
}
