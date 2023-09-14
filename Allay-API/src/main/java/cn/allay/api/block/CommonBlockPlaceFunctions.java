package cn.allay.api.block;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.function.Place;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Allay Project 2023/9/13
 *
 * @author Cool_Loong
 */
public interface CommonBlockPlaceFunctions {
    Place STAIR_PLACE = (player, world, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace) -> {
        if (player != null) {
            var stairFace = player.getHorizontalFace();
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.WEIRDO_DIRECTION, stairFace.toStairDirectionValue());
            if ((clickPos.y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
                blockState = blockState.setProperty(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, true);
            }
        }
        world.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    };
}
