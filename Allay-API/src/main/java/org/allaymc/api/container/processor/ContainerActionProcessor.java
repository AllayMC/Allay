package org.allaymc.api.container.processor;

import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.List;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessor<T extends ItemStackRequestAction> {
    ItemStackRequestActionType getType();

    ActionResponse ERROR_RESPONSE = new ActionResponse(false, List.of());

    ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions);

    default ActionResponse error() {
        return ERROR_RESPONSE;
    }
}
