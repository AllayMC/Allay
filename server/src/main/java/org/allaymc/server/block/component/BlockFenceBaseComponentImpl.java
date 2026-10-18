package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.interfaces.BlockFenceBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.server.block.HorizontalConnectionHelper;

/**
 * Connects fences to other fences of the same kind and to solid sides.
 * <p>
 * Wooden fences join every other wooden fence, and the nether brick fence only joins nether brick fences. Fences do
 * not join fence gates, walls, panes or bars on Bedrock.
 */
public class BlockFenceBaseComponentImpl extends BlockHorizontalConnectionBaseComponentImpl {
    public BlockFenceBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    protected boolean connectsTo(BlockState self, BlockFace face, BlockState neighbor) {
        if (neighbor.getBehavior() instanceof BlockFenceBehavior) {
            return isNetherBrick(self) == isNetherBrick(neighbor);
        }
        return HorizontalConnectionHelper.isSolidSide(neighbor, face);
    }

    private static boolean isNetherBrick(BlockState fence) {
        return fence.getBlockType() == BlockTypes.NETHER_BRICK_FENCE;
    }
}
