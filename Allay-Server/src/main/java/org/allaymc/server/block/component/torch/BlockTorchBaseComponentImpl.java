package org.allaymc.server.block.component.torch;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.component.common.BlockLiquidComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "torch_facing_direction")
public class BlockTorchBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    private static TorchFacingDirection computeTorchFacingDirection(BlockFace blockFace) {
        Preconditions.checkNotNull(blockFace);
        TorchFacingDirection torchFace = TorchFacingDirection.UNKNOWN;
        switch (blockFace) {
            case UP -> torchFace = TorchFacingDirection.TOP;
            case WEST -> torchFace = TorchFacingDirection.EAST;
            case EAST -> torchFace = TorchFacingDirection.WEST;
            case NORTH -> torchFace = TorchFacingDirection.SOUTH;
            case SOUTH -> torchFace = TorchFacingDirection.NORTH;
        }
        return torchFace;
    }

    @Override
    public boolean place(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        checkPlaceMethodParam(player, dimension, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
        var oldBlock = dimension.getBlockState(placeBlockPos);
        var torchFace = computeTorchFacingDirection(blockFace);

        if ((oldBlock.getBlockType() != BlockTypes.AIR_TYPE && !(oldBlock.getBehavior() instanceof BlockLiquidComponent)) ||
                torchFace == TorchFacingDirection.UNKNOWN) return false;

        var targetBlock = dimension.getBlockState(targetBlockPos);
        if (targetBlock.getBlockType().getMaterial().isSolid()) {
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION, torchFace);
        } else {
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION, TorchFacingDirection.TOP);
            var downBlock = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
            if (!downBlock.getBlockType().getMaterial().isSolid()) return false;
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    }
}
