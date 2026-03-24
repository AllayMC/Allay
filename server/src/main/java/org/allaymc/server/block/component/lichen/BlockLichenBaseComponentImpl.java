package org.allaymc.server.block.component.lichen;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

public class BlockLichenBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockLichenBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var currentState = dimension.getBlockState(placeBlockPos);
        var currentBits = currentState.getBlockType() == blockType
                ? currentState.getPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                : 0;

        var newFace = placementInfo == null ? null : placementInfo.blockFace().opposite();
        if (newFace == null || !canAttachAt(dimension, placeBlockPos, newFace)) {
            newFace = findAttachableFace(dimension, placeBlockPos, currentBits);
        }

        if (newFace == null) {
            return false;
        }

        var newBits = currentBits | faceToBit(newFace);
        if (newBits == currentBits) {
            var fallbackFace = findAttachableFace(dimension, placeBlockPos, currentBits);
            if (fallbackFace == null) {
                return false;
            }

            newBits |= faceToBit(fallbackFace);
        }

        if (currentState.getBlockType() == blockType) {
            return dimension.setBlockState(
                    placeBlockPos.x(),
                    placeBlockPos.y(),
                    placeBlockPos.z(),
                    currentState.setPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS, newBits),
                    placementInfo
            );
        }

        return super.place(
                dimension,
                blockState.setPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS, newBits),
                placeBlockPos,
                placementInfo
        );
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var currentBits = block.getPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS);
        var supportedBits = 0;
        for (var supportFace : BlockFace.VALUES) {
            var bit = faceToBit(supportFace);
            if ((currentBits & bit) != 0 && canAttachAt(block.getDimension(), block.getPosition(), supportFace)) {
                supportedBits |= bit;
            }
        }

        if (supportedBits == 0) {
            block.breakBlock();
            return;
        }

        if (supportedBits != currentBits) {
            block.getDimension().setBlockState(
                    block.getPosition(),
                    block.getBlockState().setPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS, supportedBits)
            );
        }
    }

    protected boolean canAttachAt(Dimension dimension, Vector3ic blockPos, BlockFace supportFace) {
        var supportState = dimension.getBlockState(supportFace.offsetPos(blockPos));
        return supportState.getBlockStateData().collisionShape().isFull(supportFace.opposite());
    }

    protected BlockFace findAttachableFace(Dimension dimension, Vector3ic blockPos, int currentBits) {
        for (var supportFace : BlockFace.VALUES) {
            var bit = faceToBit(supportFace);
            if ((currentBits & bit) == 0 && canAttachAt(dimension, blockPos, supportFace)) {
                return supportFace;
            }
        }

        return null;
    }

    protected int faceToBit(BlockFace face) {
        return switch (face) {
            case DOWN -> 0b000001;
            case UP -> 0b000010;
            case SOUTH -> 0b000100;
            case WEST -> 0b001000;
            case NORTH -> 0b010000;
            case EAST -> 0b100000;
        };
    }
}
