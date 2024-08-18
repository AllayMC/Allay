package org.allaymc.server.item.component.tool;

import org.allaymc.api.block.component.BlockWoodBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.event.CItemRightClickOnBlockEvent;
import org.allaymc.api.item.component.tool.ItemAxeComponent;

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
        // TODO: Wave animation
        // TODO: increase durability
    }
}
