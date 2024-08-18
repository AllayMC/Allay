package org.allaymc.server.item.component.tool;

import org.allaymc.api.block.component.BlockWoodBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.event.CItemRightClickOnBlockEvent;
import org.allaymc.api.item.component.tool.ItemAxeComponent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * Allay Project 2024/6/23
 *
 * @author daoge_cmd
 */
public class ItemAxeComponentImpl extends ItemBlockBreakingToolComponent implements ItemAxeComponent {
    @EventHandler
    protected void onRightClickOnBlock(CItemRightClickOnBlockEvent event) {
        var clickedBlockPos = event.getInteractInfo().clickBlockPos();
        var dimension = event.getDimension();
        var blockState = dimension.getBlockState(clickedBlockPos);
        if (!(blockState.getBehavior() instanceof BlockWoodBaseComponent woodBaseComponent) || woodBaseComponent.isStripped(blockState)) {
            return;
        }
        var strippedBlockState = woodBaseComponent.getStrippedBlockState(blockState);
        dimension.setBlockState(clickedBlockPos, strippedBlockState);
        var player = event.getInteractInfo().player();
        player.getItemInHand().increaseDurability(1);
        player.swingArm();
        dimension.addLevelSoundEvent(
                clickedBlockPos.x() + 0.5f, clickedBlockPos.y() + 0.5f, clickedBlockPos.z() + 0.5f,
                SoundEvent.ITEM_USE_ON, blockState.blockStateHash()
        );
    }
}
