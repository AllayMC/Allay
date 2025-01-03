package org.allaymc.server.item.component.tool;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.MathUtils;
import org.allaymc.server.item.component.event.CItemRightClickOnBlockEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author daoge_cmd
 */
public class ItemHoeComponentImpl extends ItemToolComponentImpl {
    @EventHandler
    protected void onUseOnBlock(CItemRightClickOnBlockEvent event) {
        var blockPos = event.getInteractInfo().clickedBlockPos();
        var clickedBlock = event.getInteractInfo().getClickedBlockState();
        if (!clickedBlock.getBlockType().hasBlockTag(BlockTags.GRASS)) {
            // This tag represents blocks that can be tilled by a hoe.
            return;
        }

        if (event.getDimension().getBlockState(blockPos.x(), blockPos.y() + 1, blockPos.z()).getBlockType() != BlockTypes.AIR) {
            // The block above the target block must be air.
            return;
        }

        event.getDimension().setBlockState(blockPos, BlockTypes.FARMLAND.ofState(BlockPropertyTypes.MOISTURIZED_AMOUNT.createValue(0)));
        event.getDimension().addLevelSoundEvent(MathUtils.center(blockPos), SoundEvent.ITEM_USE_ON, clickedBlock.blockStateHash());
        event.getInteractInfo().player().getItemInHand().tryReduceDurability(1);
    }
}
