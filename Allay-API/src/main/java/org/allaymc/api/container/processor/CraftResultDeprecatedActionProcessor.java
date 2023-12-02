package org.allaymc.api.container.processor;

import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

/**
 * Allay Project 2023/12/2
 *
 * @author daoge_cmd
 */
public class CraftResultDeprecatedActionProcessor implements ContainerActionProcessor<CraftResultsDeprecatedAction>{
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED;
    }

    @Override
    public ActionResponse handle(CraftResultsDeprecatedAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions) {
        return null;
    }
}
