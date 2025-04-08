package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Utils;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class BlockChorusPlantBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockChorusPlantBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (!isValidChorusPlantPosition(current)) {
            current.dimension().breakBlock(current.pos());
        }
    }

    private boolean isValidChorusPlantPosition(BlockStateWithPos current) {
        boolean hasHorizontalNeighbor = false;
        boolean hasSupportFromNeighbor = false;

        var blockBelow = current.offsetPos(BlockFace.DOWN);
        var blockAbove = current.offsetPos(BlockFace.UP);

        for (var face : BlockFace.getHorizontalBlockFaces()) {
            var neighbor = current.offsetPos(face);

            if (neighbor.blockState().getBlockType() == BlockTypes.CHORUS_PLANT) {
                if (!hasHorizontalNeighbor) {
                    if (blockAbove.blockState().getBlockType() != BlockTypes.AIR && blockBelow.blockState().getBlockType() != BlockTypes.AIR) {
                        return false;
                    }

                    hasHorizontalNeighbor = true;
                }

                var supportBlock = neighbor.offsetPos(BlockFace.DOWN);
                if (supportBlock.blockState().getBlockType() == BlockTypes.CHORUS_PLANT || supportBlock.blockState().getBlockType() == BlockTypes.END_STONE) {
                    hasSupportFromNeighbor = true;
                }
            }
        }

        if (hasHorizontalNeighbor && hasSupportFromNeighbor) {
            return true;
        }

        return blockBelow.blockState().getBlockType() == BlockTypes.CHORUS_PLANT || blockBelow.blockState().getBlockType() == BlockTypes.END_STONE;
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return Set.of(ItemTypes.CHORUS_FRUIT.createItemStack());
        }

        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return ItemTypes.AIR.createItemStack();
    }
}
