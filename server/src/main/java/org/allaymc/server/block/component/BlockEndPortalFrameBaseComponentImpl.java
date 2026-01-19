package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.END_PORTAL_EYE_BIT;

/**
 * @author IWareQ
 */
public class BlockEndPortalFrameBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockEndPortalFrameBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedBlockState = interactInfo.getClickedBlock();
        if (clickedBlockState.getPropertyValue(END_PORTAL_EYE_BIT) || itemStack.getItemType() != ItemTypes.ENDER_EYE) {
            return false;
        }

        clickedBlockState.updateBlockProperty(END_PORTAL_EYE_BIT, true);
        clickedBlockState.addSound(SimpleSound.END_PORTAL_FRAME_FILLED);
        interactInfo.player().tryConsumeItemInHand();

        // Update comparators that may be reading this end portal frame
        dimension.updateComparatorOutputLevel(interactInfo.clickedBlockPos());

        return true;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        // Output 15 if eye is placed, 0 otherwise
        return block.getPropertyValue(END_PORTAL_EYE_BIT) ? 15 : 0;
    }
}
