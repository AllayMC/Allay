package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

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
        clickedBlockState.addLevelSoundEvent(SoundEvent.BLOCK_END_PORTAL_FRAME_FILL);
        interactInfo.player().tryConsumeItemInHand();
        return true;
    }
}
