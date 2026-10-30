package org.allaymc.server.block.component.grass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;

/**
 * {@code BlockShortGrassBaseComponentImpl} implements dirt support and seed drops for short
 * grass. Lower-face changes remove grass whose supporting type lacks the dirt tag. Ordinary
 * breaking uses the shared Fortune divisor helper to choose between one wheat seed and an
 * empty drop set.
 *
 * @author daoge_cmd
 */
public class BlockShortGrassBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the short grass behavior component for the supplied block type.
     */
    public BlockShortGrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks grass after invalid lower support
     * changes. Other neighbor faces leave the local support rule untouched, while the dirt tag
     * determines accepted lower block types.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !canPlaceOn(neighbor.getBlockType())) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether the supporting block type carries the dirt tag.
     */
    protected boolean canPlaceOn(BlockType<?> blockType) {
        return blockType.hasBlockTag(BlockTags.DIRT);
    }

    /**
     * Returns one wheat seed when the Fortune-adjusted divisor roll succeeds, otherwise an
     * empty set. The base divisor is eight and each Fortune level subtracts two, with the
     * shared helper clamping its divisor to at least one.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 8, 2)) {
            return Set.of(ItemTypes.WHEAT_SEEDS.createItemStack());
        }

        return Set.of();
    }
}
