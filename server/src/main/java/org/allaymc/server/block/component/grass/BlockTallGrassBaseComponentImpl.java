package org.allaymc.server.block.component.grass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.data.ItemId;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;

/**
 * Suitable for two-block high plants that can drop wheat seeds.
 *
 * @author daoge_cmd | Dhaiven
 */
public class BlockTallGrassBaseComponentImpl extends BlockShortGrassBaseComponentImpl {

    protected final ItemId shearDrop;

    public BlockTallGrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId shearDrop) {
        super(blockType);
        this.shearDrop = shearDrop;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo) &&
               dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z(), blockState.setPropertyValue(UPPER_BLOCK_BIT, true), placementInfo);
    }

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

    protected boolean isSamePlant(BlockState otherBlock) {
        return otherBlock.getBlockType() == blockType;
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return false;
        }

        // Don't drop if entity is null
        return entity != null && super.isDroppable(block, usedItem, entity);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return Set.of(shearDrop.getItemType().createItemStack(2));
        }

        return super.getDrops(block, usedItem, entity);
    }
}
