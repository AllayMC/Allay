package cn.allay.api.block.interfaces.stairs;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.base.BlockBaseComponentImpl;
import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/29
 *
 * @author daoge_cmd
 */
public class BlockStairsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockStairsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(@Nullable EntityPlayer player, @NotNull World world, @NotNull BlockState blockState, @NotNull Vector3ic targetBlockPos, @NotNull Vector3ic placeBlockPos, Vector3fc clickPos, @NotNull BlockFace blockFace) {
        if (player != null) {
            var stairFace = player.getHorizontalFace();
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.WEIRDO_DIRECTION, stairFace.toStairDirectionValue());
            if ((clickPos.y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
                blockState = blockState.setProperty(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, true);
            }
        }
        world.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    }
}
