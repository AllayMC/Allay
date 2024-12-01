package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;

/**
 * @author IWareQ
 */
public class BlockCarpetBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCarpetBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        var pos = current.pos();
        var down = pos.dimension().getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return down != BlockTypes.AIR;
    }
}
