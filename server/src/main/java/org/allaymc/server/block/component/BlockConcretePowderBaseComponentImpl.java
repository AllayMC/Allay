package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
public class BlockConcretePowderBaseComponentImpl extends BlockBaseComponentImpl implements BlockConcretePowderBaseComponent {
    protected BlockId solidBlockId;

    public BlockConcretePowderBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId solidBlockId) {
        super(blockType);
        this.solidBlockId = solidBlockId;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (hasAdjacentWater(current.dimension(), current.pos())) {
            current.dimension().setBlockState(current.pos(), getSolidBlock().getDefaultState());
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (hasAdjacentWater(dimension, placeBlockPos)) {
            blockState = getSolidBlock().getDefaultState();
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public BlockType<?> getSolidBlock() {
        return solidBlockId.getBlockType();
    }
}
