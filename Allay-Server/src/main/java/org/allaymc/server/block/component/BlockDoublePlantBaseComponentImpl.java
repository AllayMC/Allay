package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.AllayBlockTags;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/6/15
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "upper_block_bit")
public class BlockDoublePlantBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockDoublePlantBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public ItemStack[] getDrops(BlockState blockState, ItemStack usedItem) {
        if (blockState.getBlockType() == BlockTypes.LILAC_TYPE ||
                blockState.getBlockType() == BlockTypes.TALL_GRASS_TYPE) {
            var rand = ThreadLocalRandom.current();
            if (rand.nextInt(8) == 0) {
                return new ItemStack[]{ItemTypes.WHEAT_SEEDS_TYPE.createItemStack()};
            }
            return Utils.EMPTY_ITEM_STACK_ARRAY;
        } else {
            return super.getDrops(blockState, usedItem);
        }
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face != BlockFace.UP && face != BlockFace.DOWN) return;
        var dimension = current.pos().dimension();
        var isUpperBlock = current.blockState().getPropertyValue(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT);
        var plantType = current.blockState().getBlockType();
        var willBreak = false;
        if (isUpperBlock) {
            willBreak = notSamePlant(
                    dimension.getBlockState(BlockFace.DOWN.offsetPos(current.pos())),
                    plantType
            );
        } else {
            willBreak = notSamePlant(
                    dimension.getBlockState(BlockFace.UP.offsetPos(current.pos())),
                    plantType
            );
        }
        if (willBreak) dimension.breakBlock(current.pos(), null, null);
    }

    protected boolean notSamePlant(BlockState downBlock, BlockType<?> plantType) {
        return !downBlock.getBlockType().hasBlockTag(AllayBlockTags.DOUBLE_PLANT) || downBlock.getBlockType() != plantType;
    }
}
