package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.facing.BlockPillarAxisLikeComponentImpl;

import static org.allaymc.api.item.ItemHelper.isAxe;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "pillar_axis")
public class BlockWoodBaseComponentImpl extends BlockPillarAxisLikeComponentImpl {
    protected final VanillaBlockId strippedType;

    public BlockWoodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, VanillaBlockId strippedType) {
        super(blockType);
        this.strippedType = strippedType;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;

        if (isAxe(itemStack.getItemType())) {
            dimension.setBlockState(interactInfo.clickBlockPos(), strippedType.getBlockType().copyPropertyValuesFrom(dimension.getBlockState(interactInfo.clickBlockPos())));
        }

        return true;
    }
}
