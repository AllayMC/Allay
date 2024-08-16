package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.facing.BlockPillarAxisComponentImpl;

import static org.allaymc.api.item.ItemHelper.isAxe;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
public class BlockWoodBaseComponentImpl extends BlockPillarAxisComponentImpl {
    protected final BlockType<?> strippedType;

    public BlockWoodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockType<?> strippedType) {
        super(blockType);
        this.strippedType = strippedType;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;

        if (isAxe(itemStack.getItemType())) {
            dimension.setBlockState(interactInfo.clickBlockPos(), strippedType.ofState(dimension.getBlockState(interactInfo.clickBlockPos()).blockStateHash()));
        }

        return true;
    }
}
