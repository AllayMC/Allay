package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.BlockPlaceHelper;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockHangingSignBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHangingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) return super.place(dimension, blockState, placeBlockPos, null);

        var face = placementInfo.blockFace();
        if (face == BlockFace.UP) return false;
        if (face == BlockFace.DOWN) {
            blockState = blockState.withPropertyValue(BlockPropertyTypes.HANGING, true);
            var upperBlock = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
            if (!upperBlock.getBlockStateData().isSolid()) return false;

            var shape = upperBlock.getBlockStateData().collisionShape();
            var full = shape.isFull(BlockFace.DOWN);
            var centerFull = shape.isCenterFull(BlockFace.DOWN);
            if (placementInfo.player().isSneaking() || (centerFull && !full)) {
                blockState = blockState.withPropertyValue(BlockPropertyTypes.ATTACHED_BIT, true);
                blockState = BlockPlaceHelper.processGroundSignDirectionProperty(blockState, placeBlockPos, placementInfo);
            } else if (full) {
                blockState = BlockPlaceHelper.processFacingDirectionProperty(blockState, placeBlockPos, placementInfo);
            } else return false;
        } else {
            blockState = blockState.withPropertyValue(BlockPropertyTypes.FACING_DIRECTION, face.opposite().rotateY().ordinal());
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (!current.blockState().getPropertyValue(BlockPropertyTypes.HANGING) || face != BlockFace.UP) {
            return;
        }

        var keep = true;
        var upperBlock = neighbor.blockState();
        if (!upperBlock.getBlockStateData().isSolid()) {
            keep = false;
        } else {
            keep = upperBlock.getBlockStateData().collisionShape().isCenterFull(BlockFace.DOWN);
        }

        if (!keep) {
            current.pos().dimension().breakBlock(current.pos());
        }
    }
}
