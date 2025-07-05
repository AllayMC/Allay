package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.Objects;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * @author IWareQ
 */
public class BlockLadderBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockLadderBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (face.isVertical()) {
            return;
        }

        var facingDirection = current.getPropertyValue(FACING_DIRECTION);
        var blockFace = Objects.requireNonNull(BlockFace.fromId(facingDirection));
        if (!canBeSupportedAt(current.offsetPos(blockFace.opposite()), blockFace)) {
            current.breakBlock();
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var blockFace = placementInfo.blockFace();
        if (blockFace.isVertical()) {
            return false;
        }

        if (!canBeSupportedAt(placementInfo.getClickedBlockState(), blockFace)) {
            blockFace = findValidFace(dimension, placeBlockPos);
        }

        if (blockFace == null) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(FACING_DIRECTION, blockFace.ordinal()));
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    private BlockFace findValidFace(Dimension dimension, Vector3ic placeBlockPos) {
        for (var face : new BlockFace[]{BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.EAST}) {
            if (canBeSupportedAt(dimension.getBlockState(face.offsetPos(placeBlockPos)), face.opposite())) {
                return face.opposite();
            }
        }

        return null;
    }

    private boolean canBeSupportedAt(BlockState blockState, BlockFace face) {
        var shape = blockState.getBlockStateData().collisionShape();
        return shape.isFull(face);
    }
}
