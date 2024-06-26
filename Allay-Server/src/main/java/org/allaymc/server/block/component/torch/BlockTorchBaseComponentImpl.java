package org.allaymc.server.block.component.torch;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.component.common.BlockLiquidComponent;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Objects;

@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "torch_facing_direction")
public class BlockTorchBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var oldBlock = dimension.getBlockState(placeBlockPos);
        var torchFace = computeTorchFacingDirection(placementInfo.blockFace());

        // TODO: Replace when a method appears to check whether the block can be replaced
        if (
                (
                        oldBlock.getBlockType() != BlockTypes.AIR_TYPE &&
                        !(oldBlock.getBehavior() instanceof BlockLiquidComponent)
                ) || torchFace == TorchFacingDirection.UNKNOWN
        ) return false;

        var targetBlock = dimension.getBlockState(placementInfo.clickBlockPos());
        if (this.canPlaceOnBlock(targetBlock.getBlockType())) {
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION, torchFace);
        } else {
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION, TorchFacingDirection.TOP);
            var downBlock = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
            if (!this.canPlaceOnBlock(downBlock.getBlockType())) return false;
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face == BlockFace.UP) return true;
        var block = current.pos().dimension().getBlockState(face.offsetPos(current.pos()));
        return this.canPlaceOnBlock(block.getBlockType());
    }

    @Override
    public boolean canPlaceOnBlock(BlockType<?> blockType) {
        return blockType.getMaterial().isSolid();
    }

    protected TorchFacingDirection computeTorchFacingDirection(BlockFace blockFace) {
        var torchFace = TorchFacingDirection.UNKNOWN;
        switch (Objects.requireNonNull(blockFace)) {
            case UP -> torchFace = TorchFacingDirection.TOP;
            case WEST -> torchFace = TorchFacingDirection.EAST;
            case EAST -> torchFace = TorchFacingDirection.WEST;
            case NORTH -> torchFace = TorchFacingDirection.SOUTH;
            case SOUTH -> torchFace = TorchFacingDirection.NORTH;
        }
        return torchFace;
    }
}
