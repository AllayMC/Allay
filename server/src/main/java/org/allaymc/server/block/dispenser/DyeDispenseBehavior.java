package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockSaplingBehavior;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

public class DyeDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        if (item.getItemType() != ItemTypes.BONE_MEAL && item.getItemType() != ItemTypes.RAPID_FERTILIZER) {
            return DefaultDispenseBehavior.INSTANCE.dispense(block, face, item);
        }

        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);

        var behavior = targetBlock.getBehavior();
        if (!(behavior instanceof BlockFertilizableComponent fertilizable)) {
            return DispenseResult.fail();
        }

        if (fertilizable.onBoneMealUsed(dimension, targetPos, targetBlock)) {
            return DispenseResult.success();
        }

        if (behavior instanceof BlockSaplingBehavior) {
            return DispenseResult.success();
        }

        return DispenseResult.fail();
    }
}