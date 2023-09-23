package cn.allay.api.item;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.world.World;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/2
 *
 * @author daoge_cmd
 */
public interface CommonUseItemFunctions {
    static UseItemOn createPlaceBlockUseOn() {
        return (player, itemStack, world, targetBlockPos, placeBlockPos, clickPos, blockFace) -> {
            var blockState = itemStack.toBlockState();
            if (blockState == null)
                return false;
            if (player != null && hasEntityCollision(world, placeBlockPos, blockState))
                return false;
            BlockType<?> blockType = itemStack.getItemType().getBlockType();
            assert blockType != null;
            boolean result = blockType.getBlockBehavior().place(player, world, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
            tryConsumeItem(player, itemStack);
            return result;
        };
    }

    static void tryConsumeItem(EntityPlayer player, ItemStack itemStack) {
        if (player == null || player.getClient().getGameType() != GameType.CREATIVE)
            itemStack.setCount(itemStack.getCount() - 1);
    }

    static boolean hasEntityCollision(World world, Vector3ic placePos, BlockState blockState) {
        var block_aabb = blockState.getBehavior().getBlockAttributes(blockState)
                .computeOffsetVoxelShape(
                        placePos.x(),
                        placePos.y(),
                        placePos.z()
                );
        return !world.getEntityPhysicsService().computeCollidingEntities(block_aabb).isEmpty();
    }
}
