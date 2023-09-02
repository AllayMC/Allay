package cn.allay.api.item;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.entity.impl.EntityPlayer;
import cn.allay.api.world.World;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/2
 *
 * @author daoge_cmd
 */
public interface CommonUseItemFunctions {
    UseItemOn ITEM_STAIR_USE_ON = (player, itemStack, world, blockPos, placePos, clickPos, blockFace) -> {
        var blockState = itemStack.toBlockState();
        if (blockState == null)
            return false;
        if (player != null && !checkEntityCollision(world, placePos, blockState)) return false;
        if (player != null) {
            var stairFace = player.getHorizontalFace();
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.WEIRDO_DIRECTION, stairFace.toStairDirectionValue());
            if ((clickPos.y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
                blockState = blockState.setProperty(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, true);
            }
        }
        world.setBlockState(placePos.x(), placePos.y(), placePos.z(), blockState);
        tryConsumeItem(player, itemStack);
        return true;
    };

    static void tryConsumeItem(EntityPlayer player, ItemStack itemStack) {
        if (player == null || player.getClient().getGameType() != GameType.CREATIVE)
            itemStack.setCount(itemStack.getCount() - 1);
    }

    static boolean checkEntityCollision(World world, Vector3ic placePos, BlockState blockState) {
        var block_aabb = blockState.getBehavior().getBlockAttributes(blockState)
                .computeOffsetVoxelShape(
                        placePos.x(),
                        placePos.y(),
                        placePos.z()
                );
        if (!world.getEntityPhysicsService().computeCollidingEntities(block_aabb).isEmpty())
            return false;
        return true;
    }
}
