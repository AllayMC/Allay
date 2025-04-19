package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Objects;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockWallSignBaseComponentImpl extends BlockBaseComponentImpl {
    protected ItemId dropItemId;

    public BlockWallSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId dropItemId) {
        super(blockType);
        this.dropItemId = dropItemId;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        var signFace = Objects.requireNonNull(BlockFace.fromId(current.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION)));
        if (face == signFace.opposite() && !neighbor.getBlockStateData().isSolid()) {
            current.breakBlock();
        }
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemId.getItemType().createItemStack(1));
    }

    @Override
    public ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return dropItemId.getItemType().createItemStack(1);
    }
}
