package cn.allay.api.block.interfaces.torch;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.base.BlockBaseComponentImpl;
import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.property.enums.TorchFacingDirection;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

public class BlockTorchBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(@Nullable EntityPlayer player, @NotNull World world, @NotNull BlockState blockState, @NotNull Vector3ic targetBlockPos, @NotNull Vector3ic placeBlockPos, Vector3fc clickPos, @NotNull BlockFace blockFace) {
        // TODO need to know if it can place, and need to know if it can layer place
        if (player != null) {
            TorchFacingDirection torchFace = getFace(blockFace);
            if (torchFace == TorchFacingDirection.UNKNOWN) return false;
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION, torchFace);
            world.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }
        return true;
    }

    private static TorchFacingDirection getFace(@NotNull BlockFace blockFace) {
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
}
