package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;

/**
 * {@code BlockPumpkinBaseComponentImpl} implements pumpkin carving through shears interaction.
 * The inherited interaction hook has priority, and an unhandled shears use replaces the
 * pumpkin with a carved pumpkin while copying compatible state properties and applying one
 * point of tool damage.
 *
 * @author daoge_cmd
 */
public class BlockPumpkinBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the pumpkin behavior component for the supplied block type.
     */
    public BlockPumpkinBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Carves a pumpkin when the inherited interaction does not handle a shears use. The carved
     * state copies compatible properties from the clicked block, and the tool receives a
     * one-point damage attempt after the replacement call.
     */
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
                BlockTypes.CARVED_PUMPKIN.copyPropertyValuesFrom(interactInfo.getClickedBlock().getBlockState())
        );
        itemStack.tryIncreaseDamage(1);
        return true;
    }
}
