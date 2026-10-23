package org.allaymc.server.block.component.grass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.data.ItemId;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.function.Supplier;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * {@code BlockTallGrassBaseComponentImpl} implements two-block grass placement and counterpart
 * support checks. The lower position is written first, and the upper position receives the
 * upper-half flag only if that first write succeeds.
 *
 * <p>
 * Neighbor changes preserve each half only while its required ground or same-type counterpart
 * remains. The two placement writes do not provide rollback when the upper write fails, and
 * the support predicate compares type rather than validating the other half's flag. Drop
 * eligibility suppresses upper-half duplication; lower grass also requires a breaking entity
 * before the inherited eligibility rule runs.
 *
 * @author daoge_cmd | Dhaiven
 */
public class BlockTallGrassBaseComponentImpl extends BlockShortGrassBaseComponentImpl {

    /**
     * {@code shearDropItemType} resolves the item used for shears harvests. The supplier is
     * retained and evaluated when drops are requested rather than at component construction.
     */
    protected final Supplier<ItemType<?>> shearDropItemType;

    /**
     * Creates two-block grass behavior with a deferred shears-drop supplier. The supplier is
     * retained without evaluation or validation; shears harvesting requires it to return a
     * usable item type.
     */
    public BlockTallGrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<ItemType<?>> shearDropItemTypeSupplier) {
        super(blockType);
        this.shearDropItemType = shearDropItemTypeSupplier;
    }

    /**
     * Creates two-block grass behavior with an identifier-backed shears drop. The method
     * reference defers item registry lookup until the grass is harvested with shears rather
     * than resolving it during construction.
     */
    public BlockTallGrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId shearDrop) {
        this(blockType, shearDrop::getItemType);
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
     * Allows ordinary drops only for a lower half broken by an entity. A {@code null} entity
     * rejects drops, while other lower-half calls still obey inherited creative-mode and
     * required-tool restrictions.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return false;
        }

        return entity != null && super.isDroppable(block, usedItem, entity);
    }

    /**
     * Returns two configured plant items for shears harvesting and otherwise delegates to
     * short-grass seed drops. A {@code null} tool follows that inherited chance path rather
     * than attempting to resolve the shears-drop supplier.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return Set.of(shearDropItemType.get().createItemStack(2));
        }

        return super.getDrops(block, usedItem, entity);
    }
}
