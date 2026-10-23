package org.allaymc.server.block.component.pressureplate;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.PressurePlateSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * {@code BlockPressurePlateBaseComponentImpl} manages pressure plate support, entity
 * detection, stored signal strength, and redstone notifications. The base signal
 * calculation is binary: any entity intersecting the plate's offset collision bounds
 * produces maximum power. Weighted subclasses override that calculation while retaining
 * the same activation and recheck sequence.
 *
 * <p>
 * Entity entry starts evaluation only while the stored signal is zero. An active plate
 * relies on scheduled checks to notice departing entities and intermediate weighted
 * changes. {@code updateSignal} compares the old and new signals so only transitions
 * between inactive and active states play sounds. Every signal change notifies both the
 * plate's surroundings and the support below, which can relay the plate's downward
 * strong power.
 *
 * @author daoge_cmd
 */
public class BlockPressurePlateBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code CHECK_INTERVAL} controls how often an active plate resamples entity
     * intersections until its calculated signal returns to zero.
     */
    protected static final Duration CHECK_INTERVAL = Duration.ofMillis(500);

    /**
     * Creates the pressure plate component for the supplied block type.
     */
    public BlockPressurePlateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a pressure plate only above a full upper support surface. Missing support
     * returns {@code false} before the dimension is asked to place the supplied state,
     * including for programmatic placement.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            return false;
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Checks support loss after inherited neighbor handling and breaks an unsupported
     * plate. Only updates from {@code DOWN} test the upper support surface; other
     * neighbor changes have no additional effect here.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            block.breakBlock();
        }
    }

    /**
     * Evaluates an inactive plate when an entity enters its shape. A nonzero calculated
     * signal activates the plate, while an already active plate leaves changes to its
     * scheduled entity rechecks.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        int currentSignal = block.getPropertyValue(REDSTONE_SIGNAL);
        if (currentSignal == 0) {
            int newSignal = calculateSignal(block);
            if (newSignal > 0) {
                updateSignal(block, newSignal);
            }
        }
    }

    /**
     * Recalculates an active plate's signal from current entity intersections and
     * updates a changed value. {@code currentSignal} comes from the supplied block
     * snapshot, while {@code calculateSignal} queries entities in the dimension.
     *
     * <p>
     * A positive result schedules another check even when the signal is unchanged,
     * allowing departures to be detected after entry callbacks stop. A zero result ends
     * this periodic chain; a later entity entry can start it again through
     * {@code onEntityInside}.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        int currentSignal = block.getPropertyValue(REDSTONE_SIGNAL);
        int newSignal = calculateSignal(block);

        if (newSignal != currentSignal) {
            updateSignal(block, newSignal);
        }

        // Entry callbacks stop initiating checks once the plate is active; this
        // reschedule keeps departures and weighted-strength changes observable.
        if (newSignal > 0) {
            block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), CHECK_INTERVAL);
        }
    }

    /**
     * Returns maximum power when any entity intersects the plate and {@code 0}
     * otherwise. Weighted subclasses replace this binary calculation, while the shared
     * activation path stores the result as {@code REDSTONE_SIGNAL}.
     */
    protected int calculateSignal(Block block) {
        int entityCount = countEntitiesOnPlate(block);
        return entityCount > 0 ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Counts dimension entities whose offset bounds intersect the plate's offset
     * collision bounds. This scan does not filter entity kinds or inspect the
     * callback's triggering entity, so all intersecting entities contribute equally.
     */
    protected int countEntitiesOnPlate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        AABBd detectionAABB = block.getBlockStateData().computeOffsetShape(pos).unionAABB();

        int count = 0;
        for (Entity entity : dimension.getEntities().values()) {
            if (entity.getOffsetAABB().intersectsAABB(detectionAABB)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Requests a new stored signal and notifies circuits affected by the plate.
     * {@code oldSignal} comes from the supplied block snapshot, and the requested
     * {@code newSignal} drives subsequent sounds and scheduling without checking
     * whether the dimension accepted the property update.
     *
     * <p>
     * A zero-to-positive transition plays the activation sound and starts periodic
     * checks. A positive-to-zero transition plays the release sound. Changes between
     * positive strengths remain silent, but both the plate's neighbors and the
     * support's neighbors are notified for every call.
     */
    protected void updateSignal(Block block, int newSignal) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int oldSignal = block.getPropertyValue(REDSTONE_SIGNAL);

        dimension.updateBlockProperty(REDSTONE_SIGNAL, newSignal, pos);

        if (newSignal > 0 && oldSignal == 0) {
            block.addSound(new PressurePlateSound(block.getBlockState(), true));
            // The first active transition starts polling so the plate can later
            // release even when no additional entity-entry callback occurs.
            dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, CHECK_INTERVAL);
        } else if (newSignal == 0 && oldSignal > 0) {
            block.addSound(new PressurePlateSound(block.getBlockState(), false));
        }

        dimension.updateAround(pos);
        // The support relays downward strong power to its own neighbors, which
        // may lie outside the plate's immediate neighborhood.
        dimension.updateAround(BlockFace.DOWN.offsetPos(pos));
    }

    /**
     * Checks entity intersections using the newly placed state's collision shape after
     * inherited placement handling. A positive calculated signal activates the plate
     * immediately, so entities already occupying its position need no new entry
     * callback.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        Block newBlock = new Block(newBlockState, oldBlock.getPosition());
        int signal = calculateSignal(newBlock);
        if (signal > 0) {
            updateSignal(newBlock, signal);
        }
    }

    /**
     * Notifies circuits around the support after an active plate is replaced. Inherited
     * replacement handling runs first, and a zero signal in the old snapshot causes no
     * extra notification here.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        if (oldBlock.getPropertyValue(REDSTONE_SIGNAL) > 0) {
            oldBlock.getDimension().updateAround(BlockFace.DOWN.offsetPos(oldBlock.getPosition()));
        }
    }

    /**
     * Returns the stored plate signal as weak power on every face. The result comes
     * from {@code REDSTONE_SIGNAL} in the supplied snapshot rather than performing
     * another entity scan.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(REDSTONE_SIGNAL);
    }

    /**
     * Returns the stored plate signal as strong power only toward {@code DOWN}. Other
     * queried faces receive {@code 0}, while the supporting block can relay the
     * downward result.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (face == BlockFace.DOWN) {
            return block.getPropertyValue(REDSTONE_SIGNAL);
        }
        return 0;
    }
}
