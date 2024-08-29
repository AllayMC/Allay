package org.allaymc.server.block.component.grass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

/**
 * Suitable for two-block high plants that can drop wheat seeds
 * <p>
 * Allay Project 2024/6/18
 *
 * @author daoge_cmd
 */
public class BlockTallGrassBaseComponentImpl extends BlockShortGrassBaseComponentImpl {
    public BlockTallGrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face != BlockFace.UP && face != BlockFace.DOWN) return true;

        var dimension = current.pos().dimension();
        var isUpperBlock = current.blockState().getPropertyValue(BlockPropertyTypes.UPPER_BLOCK_BIT);
        var willBreak = false;
        if (isUpperBlock) {
            willBreak = notSamePlant(dimension.getBlockState(BlockFace.DOWN.offsetPos(current.pos())));
        } else {
            willBreak = notSamePlant(dimension.getBlockState(BlockFace.UP.offsetPos(current.pos())));
            if (!willBreak)
                willBreak = !this.canPlaceOn(dimension.getBlockState(BlockFace.DOWN.offsetPos(current.pos())).getBlockType());
        }
        return !willBreak;
    }

    protected boolean notSamePlant(BlockState downBlock) {
        return downBlock.getBlockType() != blockType;
    }
}
