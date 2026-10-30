package org.allaymc.server.item.component.tool;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.ItemUseOnBlockSound;
import org.allaymc.server.item.component.event.CItemUseOnBlockEvent;

/**
 * {@code ItemHoeToolComponentImpl} tills grass-tagged blocks into dry farmland
 * when the block above is air. The interaction plays the target block's use
 * sound, requests one durability point from the player's held item, and marks
 * the component event as handled.
 *
 * @author daoge_cmd
 */
public class ItemHoeToolComponentImpl extends ItemToolComponentImpl {
    /**
     * Turns the clicked grass-tagged block into dry farmland when its upper
     * neighbor is air. The event requires player interaction data; a successful
     * conversion plays the use sound and marks the event usable even if the held
     * item's durability request is rejected.
     */
    @EventHandler
    protected void onUseOnBlock(CItemUseOnBlockEvent event) {
        var blockPos = event.getInteractInfo().clickedBlockPos();
        var clickedBlock = event.getInteractInfo().getClickedBlock();
        if (!clickedBlock.getBlockType().hasBlockTag(BlockTags.GRASS)) {
            return;
        }

        if (event.getDimension().getBlockState(blockPos.x(), blockPos.y() + 1, blockPos.z()).getBlockType() != BlockTypes.AIR) {
            return;
        }

        // Start newly tilled farmland dry so its block behavior can update moisture
        // from nearby water during subsequent world processing.
        event.getDimension().setBlockState(blockPos, BlockTypes.FARMLAND.ofState(BlockPropertyTypes.MOISTURIZED_AMOUNT.createValue(0)));
        event.getDimension().addSound(MathUtils.center(blockPos), new ItemUseOnBlockSound(clickedBlock.getBlockState()));
        event.getInteractInfo().player().getItemInHand().tryIncreaseDamage(1);
        event.setCanBeUsed(true);
    }
}
