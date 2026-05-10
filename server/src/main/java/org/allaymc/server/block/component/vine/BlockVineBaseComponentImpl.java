package org.allaymc.server.block.component.vine;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

public class BlockVineBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockVineBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        var clickedFace = placementInfo.blockFace();
        if (clickedFace.isVertical()) {
            return false;
        }

        var supportFace = clickedFace.opposite();
        if (!canAttachAt(dimension, placeBlockPos, supportFace)) {
            return false;
        }

        var currentState = dimension.getBlockState(placeBlockPos);
        var newBits = faceToBit(supportFace);
        if (currentState.getBlockType() == BlockTypes.VINE) {
            var currentBits = currentState.getPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS);
            if ((currentBits & newBits) == newBits) {
                return false;
            }

            return dimension.setBlockState(
                    placeBlockPos.x(),
                    placeBlockPos.y(),
                    placeBlockPos.z(),
                    currentState.setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, currentBits | newBits),
                    placementInfo
            );
        }

        return super.place(
                dimension,
                blockState.setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, newBits),
                placeBlockPos,
                placementInfo
        );
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var currentBits = block.getPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS);
        var aboveState = block.offsetPos(BlockFace.UP).getBlockState();
        var aboveBits = aboveState.getBlockType() == BlockTypes.VINE
                ? aboveState.getPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS)
                : 0;

        var supportedBits = 0;
        for (var supportFace : BlockFace.getHorizontalBlockFaces()) {
            var bit = faceToBit(supportFace);
            if ((currentBits & bit) == 0) {
                continue;
            }

            if (canAttachAt(block.getDimension(), block.getPosition(), supportFace) || (aboveBits & bit) != 0) {
                supportedBits |= bit;
            }
        }

        if (supportedBits == 0 && !canHangFrom(aboveState)) {
            block.breakBlock();
            return;
        }

        if (supportedBits != currentBits) {
            block.getDimension().setBlockState(
                    block.getPosition(),
                    block.getBlockState().setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, supportedBits)
            );
        }
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        return super.isDroppable(block, usedItem, entity) &&
               usedItem != null &&
               usedItem.getItemType() == ItemTypes.SHEARS;
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    protected boolean canAttachAt(Dimension dimension, Vector3ic vinePos, BlockFace supportFace) {
        var supportState = dimension.getBlockState(supportFace.offsetPos(vinePos));
        return supportState.getBlockStateData().collisionShape().isFull(supportFace.opposite());
    }

    protected boolean canHangFrom(BlockState blockState) {
        return blockState.getBlockStateData().collisionShape().isFull(BlockFace.DOWN);
    }

    protected int faceToBit(BlockFace face) {
        return switch (face) {
            case SOUTH -> 0b0001;
            case WEST -> 0b0010;
            case NORTH -> 0b0100;
            case EAST -> 0b1000;
            default -> throw new IllegalArgumentException("Vine does not support face " + face);
        };
    }
}
