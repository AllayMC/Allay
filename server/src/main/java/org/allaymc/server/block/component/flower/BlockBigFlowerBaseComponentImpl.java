package org.allaymc.server.block.component.flower;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * {@code BlockBigFlowerBaseComponentImpl} implements two-block flower placement and
 * counterpart support checks. The lower position is written first, and the upper position
 * receives the upper-half flag only if that first write succeeds.
 *
 * <p>
 * Neighbor changes preserve each half only while its required ground or same-type counterpart
 * remains. The two placement writes do not provide rollback when the upper write fails, and
 * the support predicate compares type rather than validating the other half's flag. Drop
 * eligibility suppresses upper-half duplication; lower flowers qualify directly in this
 * override.
 *
 * @author daoge_cmd | Dhaiven
 */
public class BlockBigFlowerBaseComponentImpl extends BlockSmallFlowerBaseComponentImpl {

    /**
     * Creates the big flower behavior component for the supplied block type.
     */
    public BlockBigFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Writes the supplied lower state and then an upper-flagged state one block above. A
     * failed lower write skips the upper write, and either failure returns {@code false}.
     *
     * <p>
     * The caller must supply a suitable lower state and positions because this override
     * performs no independent support or clearance validation. A successful lower write
     * remains when upper placement fails; the method does not restore the earlier block or
     * dispatch a coordinated placement event for both halves.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        // Short-circuit the upper write after a failed lower placement. A failed
        // upper write leaves the accepted lower half intact because this sequence has no rollback.
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo) &&
               dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z(), blockState.setPropertyValue(UPPER_BLOCK_BIT, true), placementInfo);
    }

    /**
     * Breaks a half when its required lower support or same-type counterpart is lost. A lower
     * half checks upper and ground changes, while an upper half checks only the block below;
     * counterpart checks compare block type without inspecting its half flag.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        var keep = true;
        if (face == BlockFace.UP) {
            if (!block.getPropertyValue(UPPER_BLOCK_BIT)) {
                keep = isSamePlant(neighbor.getBlockState());
            }
        } else if (face == BlockFace.DOWN) {
            if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
                keep = isSamePlant(neighbor.getBlockState());
            } else {
                keep = canPlaceOn(neighbor.getBlockState().getBlockType());
            }
        }

        if (!keep) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether the other state belongs to this plant type.
     */
    protected boolean isSamePlant(BlockState otherBlock) {
        return otherBlock.getBlockType() == blockType;
    }

    /**
     * Returns whether this flower is the lower half for ordinary drop handling. This override
     * uses only the half flag and does not apply the base component's entity or tool
     * restrictions.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        return !block.getPropertyValue(UPPER_BLOCK_BIT);
    }
}
