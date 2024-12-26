package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;

/**
 * @author daoge_cmd
 */
public abstract class BlockLiquidBaseComponentImpl extends BlockBaseComponentImpl implements BlockLiquidBaseComponent {

    public BlockLiquidBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    @Override
    public boolean canCollideWithEntity() {
        return true;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockStateWithPos) {

    }

//    protected boolean canFlowInto(Position3ic pos) {
//        var dim = pos.dimension();
//        var layer0 = dim.getBlockState(pos, 0);
//        if (layer0.getBlockType() == BlockTypes.AIR) {
//            return true;
//        }
//
//        if (layer0.getBlockStateData().canContainLiquid()) {
//            var layer1 = dim.getBlockState(pos, 1);
//            if (layer1)
//        }
//    }
}
