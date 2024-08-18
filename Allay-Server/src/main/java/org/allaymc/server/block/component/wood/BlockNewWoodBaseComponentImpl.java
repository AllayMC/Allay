package org.allaymc.server.block.component.wood;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

import static org.allaymc.api.item.ItemHelper.isAxe;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "pillar_axis")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "stripped_bit")
public class BlockNewWoodBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockNewWoodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;

        if (isAxe(itemStack.getItemType())) {
            dimension.setBlockState(interactInfo.clickBlockPos(), dimension.getBlockState(interactInfo.clickBlockPos()).setProperty(VanillaBlockPropertyTypes.STRIPPED_BIT, true));
        }

        return false;
    }
}
