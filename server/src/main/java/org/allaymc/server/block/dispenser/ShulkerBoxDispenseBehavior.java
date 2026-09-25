package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.item.ItemStack;

public class ShulkerBoxDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlockState = dimension.getBlockState(targetPos);
        if (!targetBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return DispenseResult.fail();
        }
        if (!item.placeBlock(dimension, targetPos, null)) {
            return DispenseResult.fail();
        }
        return DispenseResult.success();
    }
}