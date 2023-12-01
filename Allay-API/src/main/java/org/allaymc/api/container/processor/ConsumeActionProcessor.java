package org.allaymc.api.container.processor;

import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
public class ConsumeActionProcessor implements ContainerActionProcessor<ConsumeAction> {
    @Override
    public ActionResponse handle(ConsumeAction action, EntityPlayer player) {
        // TODO: 当且仅当遇到硬编码配方(MultiRecipe)时信任客户端物品消耗动作
        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CONSUME;
    }
}
