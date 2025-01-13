package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockSlabBaseComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public class BlockSlabBaseComponentImpl extends BlockBaseComponentImpl implements BlockSlabBaseComponent {
    protected BlockId doubleSlabId;

    public BlockSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId doubleSlabId) {
        super(blockType);
        this.doubleSlabId = doubleSlabId;
    }

    @Override
    public BlockType<?> getDoubleSlabBlockType() {
        return doubleSlabId.getBlockType();
    }
}
