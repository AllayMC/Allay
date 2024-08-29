package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;

/**
 * Allay Project 2024/8/15
 *
 * @author daoge_cmd
 */
public class BlockWallSignBaseComponentImpl extends BlockBaseComponentImpl {

    protected ItemType<?> dropItemType;

    public BlockWallSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemType<?> dropItemType) {
        super(blockType);
        this.dropItemType = dropItemType;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        var signFaceOpposite = BlockFace.fromId(current.blockState().getPropertyValue(BlockPropertyTypes.FACING_DIRECTION)).opposite();
        if (face != signFaceOpposite) return true;
        return neighbor.blockState().getBlockType().getMaterial().isSolid();
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemType.createItemStack(1));
    }

    @Override
    public ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return dropItemType.createItemStack(1);
    }
}
