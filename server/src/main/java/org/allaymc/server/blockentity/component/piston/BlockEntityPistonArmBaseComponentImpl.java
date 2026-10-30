package org.allaymc.server.blockentity.component.piston;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityPistonArmBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityMovingBlock;
import org.allaymc.api.blockentity.interfaces.BlockEntityPistonArm;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code BlockEntityPistonArmBaseComponentImpl} advances piston animation,
 * attempts entity displacement, and restores blocks carried by moving
 * placeholders. Preparation broadcasts equal initial phases before callers
 * create moving blocks; {@code startMoving} then separates the phases by
 * {@code MOVE_STEP}. Each tick advances both values and clamps them at the
 * movement endpoint. At the default quarter-step, the main phase reaches its
 * endpoint after four processing ticks, and the lagging phase converges on the
 * fifth.
 *
 * <p>
 * Completion marks the arm finished before replacing placeholders with their
 * carried states and replaying optional clean-save entity payloads. Additional
 * neighbor updates follow those restoration attempts, and retraction then
 * removes an arm-collision block. The sequence has no rollback and ignores
 * block placement return flags. NBT stores phases and flattened attached
 * coordinates, but it does not reconstruct {@code finished} or the captured
 * original-state map, so loading an in-progress snapshot alone does not resume
 * the animation.
 *
 * @author daoge_cmd
 */
public class BlockEntityPistonArmBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityPistonArmBaseComponent {

    /**
     * {@code TAG_STATE} names the persisted motion-state byte. The piston tag
     * constants identify phase values, configuration, and flattened attached
     * positions during NBT exchange.
     */
    protected static final String TAG_STATE = "State";
    /**
     * Persisted paired motion-state byte.
     */
    protected static final String TAG_NEW_STATE = "NewState";
    /**
     * Persisted main animation phase.
     */
    protected static final String TAG_PROGRESS = "Progress";
    /**
     * Persisted lagging animation phase.
     */
    protected static final String TAG_LAST_PROGRESS = "LastProgress";
    /**
     * Persisted sticky piston flag.
     */
    protected static final String TAG_STICKY = "Sticky";
    /**
     * Persisted outward animation flag.
     */
    protected static final String TAG_EXTENDING = "Extending";
    /**
     * Persisted flattened source coordinates.
     */
    protected static final String TAG_ATTACHED_BLOCKS = "AttachedBlocks";
    /**
     * Persisted outward face ordinal.
     */
    protected static final String TAG_FACING = "facing";
    /**
     * Persisted remembered redstone input.
     */
    protected static final String TAG_POWERED = "powered";

    /**
     * Injected owning piston-arm entity.
     */
    @ComponentObject
    protected BlockEntityPistonArm thisPistonArm;

    /**
     * {@code facing} retains the outward movement direction. The generated
     * setter does not validate or broadcast it, and movement or saving requires
     * a non-{@code null} value.
     */
    @Getter
    @Setter
    protected BlockFace facing = BlockFace.NORTH;
    /**
     * Retained sticky piston configuration.
     */
    @Getter
    @Setter
    protected boolean sticky;
    /**
     * Current outward animation selection.
     */
    @Getter
    protected boolean extending;
    // progress and lastProgress advance together after startMoving separates
    // them by MOVE_STEP. Clamping collapses that gap at the endpoint, which
    // triggers final restoration.
    /**
     * Current main animation phase.
     */
    @Getter
    protected float progress;
    /**
     * Current lagging animation phase.
     */
    @Getter
    protected float lastProgress = 1;
    // state and newState identify the same prepared direction and terminal
    // state during ordinary transitions; loading or generated setters can
    // replace either value independently.
    /**
     * Current persisted motion-state code.
     */
    @Getter
    @Setter
    protected byte state;
    /**
     * Paired persisted motion-state code.
     */
    @Getter
    @Setter
    protected byte newState = 1;
    /**
     * {@code finished} gates animation processing. Preparation clears it and
     * completion sets it before restoration; NBT loading does not derive or
     * replace this flag.
     */
    @Getter
    protected boolean finished = true;
    /**
     * Remembered active redstone input.
     */
    @Getter
    @Setter
    protected boolean powered;

    /**
     * {@code attachedBlocks} retains source-position references in a copied
     * list. Finalization resolves moving placeholders one step from those
     * positions, and the accessor copies only the list container.
     */
    protected List<Vector3ic> attachedBlocks = new ArrayList<>();
    /**
     * {@code attachedBlockStates} retains a shallow snapshot supplied during
     * preparation. Current restoration reads carried moving-block states
     * instead; completion clears this map without consulting its contents.
     */
    protected Map<Vector3ic, BlockState> attachedBlockStates = new HashMap<>();

    /**
     * Creates common piston-arm state with default north facing and finished
     * animation.
     */
    public BlockEntityPistonArmBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Builds common NBT with motion phases, state codes, configuration, and
     * attachments. Attached source positions are flattened in list order as
     * consecutive {@code x}, {@code y}, {@code z} integers so every three
     * values identify one position during loading. The retained direction must
     * be non-{@code null} for ordinal encoding. This payload does not include
     * {@code finished} or {@code attachedBlockStates}, and therefore does not
     * independently preserve all runtime state needed to continue an unfinished
     * animation.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putByte(TAG_STATE, state);
        builder.putByte(TAG_NEW_STATE, newState);
        builder.putFloat(TAG_PROGRESS, progress);
        builder.putFloat(TAG_LAST_PROGRESS, lastProgress);
        builder.putBoolean(TAG_STICKY, sticky);
        builder.putBoolean(TAG_EXTENDING, extending);
        builder.putBoolean(TAG_POWERED, powered);
        builder.putInt(TAG_FACING, facing.ordinal());

        // Flatten each source position as an ordered coordinate triple so
        // loading can reconstruct positions without losing the association
        // between their three axis values.
        List<Integer> attachedBlocksList = new ArrayList<>();
        for (var pos : attachedBlocks) {
            attachedBlocksList.add(pos.x());
            attachedBlocksList.add(pos.y());
            attachedBlocksList.add(pos.z());
        }
        builder.putList(TAG_ATTACHED_BLOCKS, NbtType.INT, attachedBlocksList);

        return builder.build();
    }

    /**
     * Loads supplied piston fields and reconstructs attached positions from
     * triples. Absent fields retain existing values, including the current
     * attachment list when its tag is missing. A supplied list clears that list
     * before decoding; a length not divisible by three fails after any earlier
     * triples have already been added. Invalid face indices can retain a
     * {@code null} direction. {@code finished} and {@code attachedBlockStates}
     * remain unchanged, so persisted state and phase values alone do not
     * restore animation processing.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_STATE, value -> state = value);
        nbt.listenForByte(TAG_NEW_STATE, value -> newState = value);
        nbt.listenForFloat(TAG_PROGRESS, value -> progress = value);
        nbt.listenForFloat(TAG_LAST_PROGRESS, value -> lastProgress = value);
        nbt.listenForBoolean(TAG_STICKY, value -> sticky = value);
        nbt.listenForBoolean(TAG_EXTENDING, value -> extending = value);
        nbt.listenForBoolean(TAG_POWERED, value -> powered = value);
        nbt.listenForInt(TAG_FACING, value -> facing = BlockFace.fromIndex(value));

        // Each saved position occupies three consecutive integers; malformed
        // trailing values fail during decoding after the attachment list has
        // already been cleared.
        nbt.listenForList(TAG_ATTACHED_BLOCKS, NbtType.INT, blocks -> {
            attachedBlocks.clear();
            for (int i = 0; i < blocks.size(); i += 3) {
                attachedBlocks.add(new Vector3i(
                        blocks.get(i),
                        blocks.get(i + 1),
                        blocks.get(i + 2)
                ));
            }
        });
    }

    /**
     * Advances both animation phases and processes collision-based entity
     * displacement. A finished arm returns immediately. Extension increases
     * both phases toward one and retraction decreases them toward zero; entity
     * displacement uses their remaining difference after that advancement. The
     * method completes restoration only when the clamped values become equal,
     * giving the lagging phase an extra processing tick after the main phase
     * first reaches its endpoint. Failures during collision handling or
     * restoration leave previously changed phases and world state intact.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        if (finished) {
            return;
        }

        // Advance both phases together; the start offset remains a displacement
        // gap until endpoint clamping makes them equal and permits final block
        // restoration.
        if (extending) {
            progress = Math.min(1, progress + MOVE_STEP);
            lastProgress = Math.min(1, lastProgress + MOVE_STEP);
        } else {
            progress = Math.max(0, progress - MOVE_STEP);
            lastProgress = Math.max(0, lastProgress - MOVE_STEP);
        }

        moveCollidedEntities();

        // The main phase can reach its endpoint before the lagging phase;
        // restore blocks only after both clamp together so the final
        // displacement pass has completed.
        if (progress == lastProgress) {
            finishAnimation();
        }
    }

    /**
     * Finds entities intersecting moving placeholders and the translated
     * piston-head cube. Each retained source position resolves a placeholder
     * one step along the selected push direction, and only compatible
     * moving-block entities contribute their collision query. The head uses a
     * separate unit cube translated by {@code progress}; horizontal cubes
     * extend upward by a quarter block. Queries do not share a deduplication
     * set, so one entity can receive displacement attempts from more than one
     * overlapping cube in this pass.
     */
    protected void moveCollidedEntities() {
        var dimension = position.dimension();
        var pushDirection = extending ? facing : facing.opposite();

        for (var pos : attachedBlocks) {
            var blockEntityPos = pushDirection.offsetPos(pos);
            var blockEntity = dimension.getBlockEntity(blockEntityPos);
            if (blockEntity instanceof BlockEntityMovingBlock movingBlock) {
                moveEntitiesForMovingBlock(movingBlock, pushDirection);
            }
        }

        var bb = new AABBd(0, 0, 0, 1, 1, 1);
        var offset = pushDirection.getOffset();
        bb = bb.translate(
                position.x() + (offset.x() * progress),
                position.y() + (offset.y() * progress),
                position.z() + (offset.z() * progress)
        );
        // Include entities standing a quarter block above horizontally moving
        // cubes.
        if (pushDirection.isHorizontal()) {
            bb = new AABBd(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY + 0.25, bb.maxZ);
        }

        var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(bb);
        for (var entity : entities) {
            moveEntity(entity, pushDirection);
        }
    }

    /**
     * Queries a moving placeholder's translated unit cube and attempts entity
     * displacement. An absent original block state suppresses this query, but a
     * present state does not replace the unit cube with that block's actual
     * collision shape. The destination position is offset by
     * {@code progress - 1} steps along the movement vector, and horizontal
     * movement extends the cube upward by a quarter block. Every returned
     * entity is passed to the shared displacement helper.
     */
    protected void moveEntitiesForMovingBlock(BlockEntityMovingBlock movingBlock, BlockFace moveDirection) {
        var blockState = movingBlock.getMovingBlockState();
        if (blockState == null) {
            return;
        }

        var dimension = position.dimension();
        var movingPos = movingBlock.getPosition();

        // Offset the destination cube by progress - 1 movement steps in either
        // direction; decreasing retraction progress moves this query offset from zero toward minus one.
        var bb = new AABBd(0, 0, 0, 1, 1, 1);
        var moveOffset = moveDirection.getOffset();
        bb = bb.translate(
                movingPos.x() + (progress * moveOffset.x()) - moveOffset.x(),
                movingPos.y() + (progress * moveOffset.y()) - moveOffset.y(),
                movingPos.z() + (progress * moveOffset.z()) - moveOffset.z()
        );
        // Include entities standing a quarter block above horizontally moving
        // cubes.
        if (moveDirection.isHorizontal()) {
            bb = new AABBd(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY + 0.25, bb.maxZ);
        }

        var entities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(bb);
        for (var entity : entities) {
            moveEntity(entity, moveDirection);
        }
    }

    /**
     * Attempts to teleport an entity by the difference between animation
     * phases. Downward directions and a zero phase difference produce no
     * attempt. Entities whose physics component disables server-side movement
     * are also skipped; other entities receive a target preserving their
     * rotation and dimension. Upward displacement doubles the phase difference,
     * while horizontal displacement uses it directly. The teleport result is
     * ignored, so cancellation or another teleport rejection does not interrupt
     * animation processing or imply that the entity moved.
     */
    protected void moveEntity(Entity entity, BlockFace moveDirection) {
        // Downward movement still restores blocks, but this entity-teleport
        // path applies no displacement for it, leaving those entities at their
        // existing locations.
        if (moveDirection == BlockFace.DOWN) {
            return;
        }

        var diff = Math.abs(progress - lastProgress);
        if (diff == 0) {
            return;
        }

        // Respect physics components that opt out of server movement so this
        // piston path does not add a server displacement to their separate
        // movement control.
        if (entity instanceof EntityPhysicsComponent physicsComponent &&
            !physicsComponent.computeMovementServerSide()) {
            return;
        }

        var entityOffset = moveDirection.getOffset();
        double dx = diff * entityOffset.x();
        // Upward movement applies twice the phase difference as vertical
        // displacement.
        double dy = diff * entityOffset.y() * (moveDirection == BlockFace.UP ? 2 : 1);
        double dz = diff * entityOffset.z();

        var location = entity.getLocation();
        var newLocation = new Location3d(
                location.x() + dx,
                location.y() + dy,
                location.z() + dz,
                location.pitch(),
                location.yaw(),
                location.dimension()
        );
        entity.teleport(newLocation);
    }

    /**
     * Marks animation finished and requests restoration of attached moving
     * placeholders. Terminal state codes are stored before world changes. For
     * each compatible placeholder, the method retains its original state and
     * payload, requests the state replacement, loads a present entity payload
     * at the destination, and invokes the original block's moved hook. A
     * missing original state instead requests air. Block-placement return flags
     * are ignored, and payload loading expects a compatible destination entity
     * without a separate existence check.
     *
     * <p>
     * Additional neighbor notifications run for recorded source and destination
     * positions after the restoration loop. Retraction then removes an
     * arm-collision block, both attachment collections are cleared, viewers
     * receive the terminal state, and a one-tick block recheck is scheduled.
     * Any failure leaves earlier changes applied and can leave {@code finished}
     * set even when restoration or cleanup did not complete.
     */
    protected void finishAnimation() {
        finished = true;
        state = newState = (byte) (extending ? 2 : 0);

        var dimension = position.dimension();
        var pushDirection = extending ? facing : facing.opposite();

        // Retain both source and destination positions for additional neighbor
        // notifications after the restoration attempts, rather than issuing
        // this extra pass per moved block.
        var updatePositions = new ArrayList<Vector3ic>();

        for (var pos : attachedBlocks) {
            var newPos = pushDirection.offsetPos(pos);

            updatePositions.add(pos);
            updatePositions.add(newPos);

            var blockEntity = dimension.getBlockEntity(newPos);
            if (blockEntity instanceof BlockEntityMovingBlock movingBlockEntity) {
                // Retain carried state and entity data before replacement can
                // remove the temporary moving-block entity that currently owns
                // those restoration inputs.
                var originalState = movingBlockEntity.getMovingBlockState();
                var movingBlockEntityNBT = movingBlockEntity.getMovingBlockEntityNBT();

                // Request the original block before replaying its entity data;
                // accepted placement creates that destination entity, but the
                // placement result is not checked here.
                if (originalState != null) {
                    dimension.setBlockState(newPos, originalState);
                    if (movingBlockEntityNBT != null) {
                        dimension.getBlockEntity(newPos).loadNBT(movingBlockEntityNBT);
                    }
                    Block movedBlock = new Block(originalState, new Position3i(newPos, dimension));
                    originalState.getBehavior().onMoved(movedBlock);
                } else {
                    // No carried original state can replace this placeholder,
                    // so request air instead of leaving an unresolved temporary
                    // moving block behind.
                    dimension.setBlockState(newPos, BlockTypes.AIR.getDefaultState());
                }
            }
        }

        for (var updatePos : updatePositions) {
            dimension.updateAllAround(updatePos);
        }

        if (!extending) {
            var armPos = facing.offsetPos(position);
            var armState = dimension.getBlockState(armPos);
            if (armState.getBlockType() == BlockTypes.PISTON_ARM_COLLISION ||
                armState.getBlockType() == BlockTypes.STICKY_PISTON_ARM_COLLISION) {
                dimension.setBlockState(armPos, BlockTypes.AIR.getDefaultState());
            }
        }

        // Discard both captured attachment collections after restoration and
        // arm cleanup; any later movement must obtain a fresh description
        // through its preparation call.
        attachedBlocks.clear();
        attachedBlockStates.clear();

        sendBlockEntityToViewers();

        // Recheck block behavior one world tick after publishing terminal
        // state, allowing the piston behavior to reevaluate its activation
        // after animation cleanup has returned.
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(position, java.time.Duration.ofMillis(50));
    }

    /**
     * Returns a new list containing the retained attached-position references.
     * Changing the returned list does not change membership here, but mutable
     * coordinate objects remain shared with the movement description.
     */
    @Override
    public List<Vector3ic> getAttachedBlocks() {
        return new ArrayList<>(attachedBlocks);
    }

    /**
     * Prepares an extension and publishes its initial phase before temporary
     * blocks exist. The method marks animation unfinished, sets both phases to
     * zero and both state codes to extending, then shallowly copies the
     * supplied list and map. Position and state objects remain shared with the
     * caller. The caller creates moving-block entities after this broadcast and
     * invokes {@code startMoving} before ticking; copy or delivery failures do
     * not restore earlier preparation fields.
     */
    @Override
    public void preExtending(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates) {
        finished = false;
        extending = true;
        progress = 0;
        lastProgress = 0;
        state = newState = 1;
        attachedBlocks = new ArrayList<>(blocksToMove);
        attachedBlockStates = new HashMap<>(originalStates);

        // Publish initial arm state before the caller creates moving blocks,
        // keeping the outgoing arm description ordered ahead of those
        // temporary-block state updates.
        sendBlockEntityToViewers();
    }

    /**
     * Prepares a retraction and publishes its initial phase before temporary
     * blocks exist. The method marks animation unfinished, sets both phases to
     * one and both state codes to retracting, then shallowly copies the
     * supplied list and map. Position and state objects remain shared with the
     * caller. The caller creates moving-block entities after this broadcast and
     * invokes {@code startMoving} before ticking; copy or delivery failures do
     * not restore earlier preparation fields.
     */
    @Override
    public void preRetracting(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates) {
        finished = false;
        extending = false;
        progress = 1;
        lastProgress = 1;
        state = newState = 3;
        attachedBlocks = new ArrayList<>(blocksToMove);
        attachedBlockStates = new HashMap<>(originalStates);

        // Publish initial arm state before the caller creates moving blocks,
        // keeping the outgoing arm description ordered ahead of those
        // temporary-block state updates.
        sendBlockEntityToViewers();
    }

    /**
     * Separates animation phases after the caller has created the moving
     * placeholders. Extension puts {@code lastProgress} one step below zero;
     * retraction puts it one step above one. The resulting gap determines
     * entity displacement until both phases clamp to the same endpoint.
     * Preparation must already have cleared {@code finished}; this method
     * changes no completion flag, sends no packet, and schedules no tick or
     * block update on its own.
     */
    @Override
    public void startMoving() {
        // Offset the second phase only after moving placeholders exist; the
        // next collision pass uses their data together with this gap to attempt
        // entity displacement.
        if (extending) {
            lastProgress = -MOVE_STEP;
        } else {
            lastProgress = 1 + MOVE_STEP;
        }
    }
}
