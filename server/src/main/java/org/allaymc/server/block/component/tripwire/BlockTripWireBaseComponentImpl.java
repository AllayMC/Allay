package org.allaymc.server.block.component.tripwire;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * {@code BlockTripWireBaseComponentImpl} tracks entity activation on tripwire segments
 * and requests reevaluation by hooks along their horizontal lines. Entry sets the
 * segment's powered bit regardless of attachment or disarmed state; hook calculation
 * separately decides whether that segment can contribute output to a complete
 * connection.
 *
 * <p>
 * Powered segments poll entity intersections until no entity remains, then clear their
 * bit and notify hooks again. Break handling marks a segment disarmed when shears are
 * used or powered otherwise before inherited drops run. Hook reevaluation is scheduled
 * rather than performed synchronously, so this component does not guarantee a
 * break-time pulse or silent removal. Searches stop at the first hook or intervening
 * non-wire block and use only facing hooks within forty-one block offsets.
 *
 * @author daoge_cmd
 */
public class BlockTripWireBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code CHECK_INTERVAL} requests entity polling every ten game ticks while a
     * tripwire snapshot remains powered, allowing departure to clear the segment.
     */
    private static final Duration CHECK_INTERVAL = Duration.ofMillis(500);

    /**
     * Creates the tripwire segment component for the supplied registered block type.
     */
    public BlockTripWireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Requests nearby hook reevaluation after inherited placement handling finishes.
     * The new-state wrapper supplies the segment position, and the helper schedules
     * hook callbacks rather than calculating their connection states immediately.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        Block newBlock = new Block(newBlockState, oldBlock.getPosition());
        updateAdjacentHooks(newBlock);
    }

    /**
     * Marks an unpowered segment active and requests hook reevaluation plus a later
     * entity check. Attachment, disarmed state, and the triggering entity's kind do not
     * gate activation in this callback; an already powered snapshot returns
     * immediately.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (block.getPropertyValue(POWERED_BIT)) {
            return;
        }

        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();
        dimension.updateBlockProperty(POWERED_BIT, true, pos);

        dimension.updateAround(pos);

        Block updatedBlock = new Block(dimension, pos);
        updateAdjacentHooks(updatedBlock);

        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, CHECK_INTERVAL);
    }

    /**
     * Checks whether an activated segment still intersects any dimension entity. The
     * current block shape supplies offset detection bounds, and the scan accepts every
     * entity kind. An unpowered supplied snapshot returns without polling or
     * rescheduling.
     *
     * <p>
     * A remaining intersection requests another delayed check, allowing activation to
     * continue after entry callbacks stop. No intersection clears the powered bit,
     * notifies immediate neighbors, and schedules facing hooks to reevaluate. The
     * property write precedes the fresh block wrapper passed to the hook-search helper.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (!block.getPropertyValue(POWERED_BIT)) {
            return;
        }

        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();

        boolean entityFound = false;
        var detectionAABB = block.getBlockStateData().computeOffsetShape(pos).unionAABB();

        for (Entity entity : dimension.getEntities().values()) {
            if (entity.getOffsetAABB().intersectsAABB(detectionAABB)) {
                entityFound = true;
                break;
            }
        }

        // Entry callbacks no longer initiate polling once POWERED_BIT is set.
        // Continue this chain while any entity remains so a later departure can release the segment.
        if (entityFound) {
            dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, CHECK_INTERVAL);
        } else {
            dimension.updateBlockProperty(POWERED_BIT, false, pos);

            dimension.updateAround(pos);

            Block updatedBlock = new Block(dimension, pos);
            updateAdjacentHooks(updatedBlock);
        }
    }

    /**
     * Marks the segment disarmed for shears or powered for other break tools before
     * inherited break handling. Each path obtains a fresh block wrapper and schedules
     * facing hooks to reevaluate the line; the non-shears path also notifies immediate
     * neighbors.
     *
     * <p>
     * These callbacks are position-based scheduled work and may observe the wire after
     * world removal rather than the temporary break-time property. This method does not
     * call hook calculation with an explicit replacement segment, so setting the bit
     * alone does not guarantee a later power pulse.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();

        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            dimension.updateBlockProperty(DISARMED_BIT, true, pos);
            Block updatedBlock = new Block(dimension, pos);
            updateAdjacentHooks(updatedBlock);
        } else {
            dimension.updateBlockProperty(POWERED_BIT, true, pos);
            dimension.updateAround(pos);
            Block updatedBlock = new Block(dimension, pos);
            updateAdjacentHooks(updatedBlock);
        }

        super.onBreak(block, usedItem, entity);
    }

    /**
     * Returns one string item when this registered block type has an item mapping. A
     * type without an item mapping returns an empty set, independently of the tool and
     * breaking entity.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (getBlockType().getItemType() != null) {
            return Set.of(ItemTypes.STRING.createItemStack());
        }
        return Set.of();
    }

    /**
     * Schedules reevaluation for facing hooks reachable along horizontal tripwire
     * segments. Each direction scans offsets one through forty-one, stopping at its
     * first hook even when that hook faces elsewhere. An intervening non-wire block
     * stops that direction before a farther hook is considered.
     *
     * <p>
     * A correctly facing hook receives a ten-millisecond request, which the block
     * update manager truncates to zero game ticks. The request remains scheduled work
     * rather than an immediate method call, and another entry at the hook position can
     * have its deadline replaced.
     */
    private void updateAdjacentHooks(Block block) {
        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();

        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            // The exclusive bound matches hook scanning: a candidate at offset
            // forty-two is outside the current connection search, even along uninterrupted string.
            for (int i = 1; i < 42; i++) {
                Vector3ic scanPos = face.offsetPos(pos, i);
                BlockState scannedState = dimension.getBlockState(scanPos);
                BlockType<?> scannedType = scannedState.getBlockType();

                if (scannedType == BlockTypes.TRIPWIRE_HOOK) {
                    int hookDirection = scannedState.getPropertyValue(DIRECTION_4);
                    BlockFace hookFacing = BlockFace.fromHorizontalIndex(hookDirection);

                    if (hookFacing == face.opposite()) {
                        // Ten milliseconds truncates to zero game ticks. Queue
                        // reevaluation at the current tick deadline instead of synchronously entering the hook calculator.
                        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(scanPos, Duration.ofMillis(10));
                    }
                    break;
                }

                if (scannedType != BlockTypes.TRIP_WIRE) {
                    break;
                }
            }
        }
    }
}
