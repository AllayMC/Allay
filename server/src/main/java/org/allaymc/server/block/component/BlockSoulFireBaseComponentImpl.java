package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;

/**
 * @author daoge_cmd
 */
public class BlockSoulFireBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSoulFireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        var downBlockType = current.offsetPos(BlockFace.DOWN).getBlockType();
        if (downBlockType != BlockTypes.SOUL_SAND && downBlockType != BlockTypes.SOUL_SOIL) {
            current.getDimension().setBlockState(current.getPos(), BlockTypes.FIRE.copyPropertyValuesFrom(current));
        }
    }
}
