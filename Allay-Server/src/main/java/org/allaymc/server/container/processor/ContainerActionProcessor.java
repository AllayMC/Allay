package org.allaymc.server.container.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessor<T extends ItemStackRequestAction> {

    ActionResponse ERROR_RESPONSE = new ActionResponse(false, List.of());

    /**
     * @param action             action object
     * @param player             the player who sent the action
     * @param currentActionIndex the index of the action in the request
     * @param actions            all actions in the request
     * @param dataPool           a map that can be used to store data between actions
     *
     * @return the response to the action
     */
    ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool);

    ItemStackRequestActionType getType();

    default ActionResponse error() {
        return ERROR_RESPONSE;
    }

    default boolean failToValidateStackNetworkId(int expectedSNID, int clientSNID) {
        // If the stackNetworkId sent by the client is less than 0, it indicates that the client ensures
        // the data integrity and requests compliance with the server's data.
        // This usually happens when an ItemStackRequest contains multiple actions and
        // multiple actions have the same source/destination container.
        // The subsequent actions do not need to repeat the check after the first action has checked the id.
        return clientSNID > 0 && expectedSNID != clientSNID;
    }
}
