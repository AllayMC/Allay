package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;

/**
 * @author daoge_cmd
 */
public class BlockPumpkinBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockPumpkinBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack.getItemType() != ItemTypes.SHEARS) {
            return false;
        }

        dimension.setBlockState(
                interactInfo.clickedBlockPos(),
                BlockTypes.CARVED_PUMPKIN.copyPropertyValuesFrom(interactInfo.getClickedBlockState())
        );
        itemStack.tryReduceDurability(1);
        return true;
    }
}
