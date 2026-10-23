package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.event.CBlockRandomUpdateEvent;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH_8;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * {@code BlockPitcherCropBaseComponentImpl} implements pitcher-crop growth as a lower half
 * that can acquire a matching upper half. Growth stops at stage four, while stages three and
 * four need an upper block with the upper-half flag. Only lower halves receive ordinary drops
 * and natural growth processing.
 *
 * <p>
 * Bone meal on an upper half redirects to the lower crop after verifying its type. Both growth
 * paths check upper space before dispatching a cancellable proposal, then write the lower
 * state and derive the upper state from the event result. The decision to create that upper
 * state uses the pre-event proposed stage, so handlers changing the replacement state do not
 * change that branch decision.
 *
 * @author daoge_cmd
 */
public class BlockPitcherCropBaseComponentImpl extends BlockCropsBaseComponentImpl {

    /**
     * {@code MAX_GROWTH} sets the terminal pitcher-crop stage used by growth rejection and
     * mature plant drops.
     */
    public static final int MAX_GROWTH = 4;
    /**
     * {@code DOUBLE_THRESHOLD} identifies the first growth stage that requires an upper half
     * and matching neighbor support.
     */
    public static final int DOUBLE_THRESHOLD = 3;

    /**
     * Creates the pitcher crop behavior component for the supplied block type.
     */
    public BlockPitcherCropBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Advances a pitcher crop by one stage or redirects an upper-half interaction to its lower
     * crop. A missing lower crop, mature stage, blocked upper space, or cancelled growth event
     * produces {@code false}.
     *
     * <p>
     * At the two-block threshold, acceptance writes the event's state at the lower position
     * and derives an upper-flagged state from it. The original proposed stage controls that
     * second write, even when a handler changes the event state. The helper reports acceptance
     * without inspecting either write result or rolling back the lower write.
     */
    @Override
    protected boolean onBoneMealUsed(Dimension dimension, Vector3ic pos, BlockState crop) {
        if (crop.getPropertyValue(UPPER_BLOCK_BIT)) {
            var lowerPos = new Vector3i(pos.x(), pos.y() - 1, pos.z());
            var lowerState = dimension.getBlockState(lowerPos);
            if (lowerState.getBlockType() != blockType) {
                return false;
            }
            return onBoneMealUsed(dimension, lowerPos, lowerState);
        }

        var growth = crop.getPropertyValue(GROWTH_8);
        if (growth >= MAX_GROWTH) {
            return false;
        }

        var newGrowth = growth + 1;

        if (newGrowth >= DOUBLE_THRESHOLD) {
            var aboveState = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z());
            var aboveType = aboveState.getBlockType();
            if (aboveType != BlockTypes.AIR && !(aboveType == blockType && aboveState.getPropertyValue(UPPER_BLOCK_BIT))) {
                return false;
            }
        }

        var newCrop = crop.setPropertyValue(GROWTH_8, newGrowth);
        var event = new BlockGrowEvent(new Block(crop, new Position3i(pos, dimension)), newCrop);
        if (event.call()) {
            // Derive both halves from the accepted state, but retain the originally
            // proposed stage for deciding whether this update creates the upper half.
            dimension.setBlockState(pos, event.getNewBlockState());
            if (newGrowth >= DOUBLE_THRESHOLD) {
                var upperState = event.getNewBlockState().setPropertyValue(UPPER_BLOCK_BIT, true);
                dimension.setBlockState(pos.x(), pos.y() + 1, pos.z(), upperState);
            }
            return true;
        }

        return false;
    }

    /**
     * Dispatches the component random-update event and processes natural growth only for a
     * lower half. Low light proposes fading before ordinary breaking; mature lower crops and
     * all upper halves stop without natural growth.
     *
     * <p>
     * An eligible probability roll advances one stage after checking upper clearance at the
     * two-block threshold. An accepted growth event writes the lower state first and then an
     * upper state derived from its replacement. The proposed stage, rather than a reread
     * event-stage value, determines whether the upper write occurs.
     */
    @Override
    public void onRandomUpdate(Block block) {
        manager.callEvent(new CBlockRandomUpdateEvent(block));

        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return;
        }

        // The lower half owns the low-light removal decision.
        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition()) < 8) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                block.breakBlock();
            }
            return;
        }

        var growth = block.getPropertyValue(GROWTH_8);
        if (growth >= MAX_GROWTH) {
            return;
        }

        if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(block)) {
            var newGrowth = growth + 1;

            if (newGrowth >= DOUBLE_THRESHOLD) {
                var above = block.offsetPos(BlockFace.UP);
                if (!above.isAir() && !(above.getBlockType() == blockType && above.getPropertyValue(UPPER_BLOCK_BIT))) {
                    return;
                }
            }

            var newCrop = block.setPropertyValue(GROWTH_8, newGrowth);
            var event = new BlockGrowEvent(block, newCrop.getBlockState());
            if (event.call()) {
                var pos = block.getPosition();
                block.getDimension().setBlockState(pos, event.getNewBlockState());
                if (newGrowth >= DOUBLE_THRESHOLD) {
                    var upperState = event.getNewBlockState().setPropertyValue(UPPER_BLOCK_BIT, true);
                    block.getDimension().setBlockState(pos.x(), pos.y() + 1, pos.z(), upperState);
                }
            }
        }
    }

    /**
     * Breaks a pitcher half whose required support or counterpart disappears. Upper halves
     * require a lower crop beneath; lower halves require farmland and, after the two-block
     * threshold, a matching upper half above.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            if (face == BlockFace.DOWN) {
                if (neighbor.getBlockType() != blockType || neighbor.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        } else {
            if (face == BlockFace.DOWN) {
                if (neighbor.getBlockType() != BlockTypes.FARMLAND) {
                    block.breakBlock();
                }
            } else if (face == BlockFace.UP && block.getPropertyValue(GROWTH_8) >= DOUBLE_THRESHOLD) {
                if (neighbor.getBlockType() != blockType || !neighbor.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        }
    }

    /**
     * Returns a pitcher plant at the mature stage and a pitcher pod beforehand. This method
     * selects by growth only; {@code isDroppable} separately suppresses ordinary drops from
     * the upper half.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH_8);
        if (growth >= MAX_GROWTH) {
            return Set.of(ItemTypes.PITCHER_PLANT.createItemStack());
        }
        return Set.of(ItemTypes.PITCHER_POD.createItemStack());
    }

    /**
     * Rejects ordinary drops for every upper crop half and delegates the lower-half decision.
     * Lower halves still obey the inherited creative-mode and required-tool rules rather than
     * always producing an item.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return false;
        }
        return super.isDroppable(block, usedItem, entity);
    }
}
