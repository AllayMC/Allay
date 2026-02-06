package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.BlockPlaceHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.HANGING;

/**
 * @author daoge_cmd
 */
public class BlockHangingSignBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHangingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        var face = placementInfo.blockFace();
        if (face == BlockFace.UP) {
            return false;
        }

        if (face == BlockFace.DOWN) {
            blockState = blockState.setPropertyValue(HANGING, true);
            var upperBlock = dimension.getBlockState(BlockFace.UP.offsetPos(placeBlockPos));
            if (!upperBlock.getBlockStateData().isSolid()) {
                return false;
            }

            var shape = upperBlock.getBlockStateData().collisionShape();
            var full = shape.isFull(BlockFace.DOWN);
            var centerFull = shape.isCenterFull(BlockFace.DOWN);
            if (placementInfo.player().isSneaking() || (centerFull && !full)) {
                blockState = blockState.setPropertyValue(BlockPropertyTypes.ATTACHED_BIT, true);
                blockState = BlockPlaceHelper.processGroundSignDirectionProperty(blockState, placeBlockPos, placementInfo);
            } else if (full) {
                blockState = BlockPlaceHelper.processFacingDirectionProperty(blockState, placeBlockPos, placementInfo);
            } else {
                return false;
            }
        } else {
            blockState = blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, face.opposite().rotateY().ordinal());
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();
        if (!block.getPropertyValue(HANGING) || face != BlockFace.UP) {
            return;
        }

        var keep = true;
        if (!neighbor.getBlockStateData().isSolid()) {
            keep = false;
        } else {
            keep = neighbor.getBlockStateData().collisionShape().isCenterFull(BlockFace.DOWN);
        }

        if (!keep) {
            block.breakBlock();
        }
    }
}
