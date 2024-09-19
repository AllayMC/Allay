package org.allaymc.server.container.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.List;
import java.util.Map;

/**
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
        // If the client's stackNetworkId is less than 0, it indicates that the client ensures data integrity and requests adherence to the server's data.
        // This usually happens when an ItemStackRequest contains multiple actions with the same source/destination container.
        // The first action checks the id, so subsequent actions do not need to repeat the check.
        return clientSNID > 0 && expectedSNID != clientSNID;
    }
}
