package org.allaymc.server.item.component.tool;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.ItemUseOnBlockSound;
import org.allaymc.server.item.component.event.CItemUseOnBlockEvent;

/**
 * @author daoge_cmd
 */
public class ItemHoeToolComponentImpl extends ItemToolComponentImpl {
    @EventHandler
    protected void onUseOnBlock(CItemUseOnBlockEvent event) {
        var blockPos = event.getInteractInfo().clickedBlockPos();
        var clickedBlock = event.getInteractInfo().getClickedBlock();
        if (!clickedBlock.getBlockType().hasBlockTag(BlockTags.GRASS)) {
            // This tag represents blocks that can be tilled by a hoe.
            return;
        }

        if (event.getDimension().getBlockState(blockPos.x(), blockPos.y() + 1, blockPos.z()).getBlockType() != BlockTypes.AIR) {
            // The block above the target block must be air.
            return;
        }

        event.getDimension().setBlockState(blockPos, BlockTypes.FARMLAND.ofState(BlockPropertyTypes.MOISTURIZED_AMOUNT.createValue(0)));
        event.getDimension().addSound(MathUtils.center(blockPos), new ItemUseOnBlockSound(clickedBlock.getBlockState()));
        event.getInteractInfo().player().getItemInHand().tryIncreaseDamage(1);
        event.setCanBeUsed(true);
    }
}
