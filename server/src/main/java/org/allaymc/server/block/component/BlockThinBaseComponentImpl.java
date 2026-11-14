package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.interfaces.BlockCopperBarsBehavior;
import org.allaymc.api.block.interfaces.BlockGlassPaneBehavior;
import org.allaymc.api.block.interfaces.BlockIronBarsBehavior;
import org.allaymc.api.block.interfaces.BlockWallBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.HorizontalConnectionHelper;

/**
 * Connects glass panes and iron or copper bars to each other, to walls and to solid sides. They do not join fences.
 */
public class BlockThinBaseComponentImpl extends BlockHorizontalConnectionBaseComponentImpl {
    public BlockThinBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    protected boolean connectsTo(BlockState self, BlockFace face, BlockState neighbor) {
        var behavior = neighbor.getBehavior();
        return behavior instanceof BlockGlassPaneBehavior ||
               behavior instanceof BlockIronBarsBehavior ||
               behavior instanceof BlockCopperBarsBehavior ||
               behavior instanceof BlockWallBehavior ||
               HorizontalConnectionHelper.isSolidSide(neighbor, face);
    }
}
