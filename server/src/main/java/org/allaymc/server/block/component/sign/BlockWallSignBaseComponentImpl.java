package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;
import org.joml.Vector3ic;

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
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal()),
                placementInfo
        );
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        var signFace = Objects.requireNonNull(BlockFace.fromIndex(block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION)));
        if (face == signFace.opposite() && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemId.getItemType().createItemStack(1));
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return dropItemId.getItemType().createItemStack(1);
    }
}
