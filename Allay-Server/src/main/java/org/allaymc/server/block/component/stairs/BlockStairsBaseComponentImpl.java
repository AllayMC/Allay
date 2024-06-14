package org.allaymc.server.block.component.stairs;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/29
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "weirdo_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "upside_down_bit")
public class BlockStairsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockStairsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        checkPlaceMethodParam(player, dimension, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
        if (player != null) {
            var stairFace = player.getHorizontalFace();
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.WEIRDO_DIRECTION, stairFace.toStairDirectionValue());
            if ((clickPos.y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
                blockState = blockState.setProperty(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, true);
            }
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    }
}
