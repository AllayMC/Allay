package org.allaymc.server.block.component.grass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
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
        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState,
                placementInfo
        );
        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z(),
                blockState.setProperty(UPPER_BLOCK_BIT, true),
                placementInfo
        );
        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face == BlockFace.UP) {
            if (!current.blockState().getPropertyValue(UPPER_BLOCK_BIT)) {
                return isSamePlant(neighbor.blockState());
            }
        } else if (face == BlockFace.DOWN) {
            if (current.blockState().getPropertyValue(UPPER_BLOCK_BIT)) {
                return isSamePlant(neighbor.blockState());
            }
            return canPlaceOn(neighbor.blockState().getBlockType());
        }

        return true;
    }

    protected boolean isSamePlant(BlockState otherBlock) {
        return otherBlock.getBlockType() == blockType;
    }

    @Override
    public boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (blockState.blockState().getPropertyValue(UPPER_BLOCK_BIT)) return false;
        // Don't drop if entity is null
        return entity != null && super.isDroppable(blockState, usedItem, entity);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return Set.of(shearDrop.getItemType().createItemStack(2));
        }

        return super.getDrops(blockState, usedItem, entity);
    }
}
