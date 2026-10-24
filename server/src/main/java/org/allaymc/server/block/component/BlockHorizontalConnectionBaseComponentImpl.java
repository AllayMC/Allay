package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.HorizontalConnectionHelper;
import org.joml.Vector3ic;

import java.util.function.Function;

/**
 * Base component for blocks that keep their four {@code minecraft:connection_*} sides in step with their neighbors.
 */
public abstract class BlockHorizontalConnectionBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHorizontalConnectionBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns whether this block connects to {@code neighbor}, which lies on the {@code face} side of it.
     *
     * @param self     the state of this block
     * @param face     the side the neighbor is on
     * @param neighbor the neighbor block state
     * @return {@code true} if the side should be connected
     */
    protected abstract boolean connectsTo(BlockState self, BlockFace face, BlockState neighbor);

    /**
     * Returns {@code state} with its connection sides resolved against the given neighbors.
     *
     * @param state     the state of this block
     * @param neighbors the neighbor lookup by direction
     * @return the state with up-to-date connection sides
     */
    public BlockState updateConnections(BlockState state, Function<BlockFace, BlockState> neighbors) {
        return HorizontalConnectionHelper.withConnections(state, face -> connectsTo(state, face, neighbors.apply(face)));
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        blockState = updateConnections(blockState, HorizontalConnectionHelper.neighbors(dimension, placeBlockPos));
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var dimension = block.getDimension();
        var updated = updateConnections(block.getBlockState(), HorizontalConnectionHelper.neighbors(dimension, block.getPosition()));
        if (!updated.equals(block.getBlockState())) {
            dimension.setBlockState(block.getPosition(), updated);
        }
    }
}
