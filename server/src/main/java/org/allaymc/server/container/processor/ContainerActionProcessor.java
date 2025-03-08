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
     * Processes an item stack request action and returns the response.
     *
     * @param action             the specific {@link ItemStackRequestAction} to handle
     * @param player             the {@link EntityPlayer} who initiated the action
     * @param currentActionIndex the index of this action within the request's action array
     * @param actions            the full array of {@link ItemStackRequestAction} objects in the request
     * @param dataPool           a {@link Map} for storing temporary data between actions in the request
     *
     * @return the {@link ActionResponse} indicating the result of processing the action
     */
    ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool);

    /**
     * Retrieves the type of action this processor handles.
     *
     * @return the {@link ItemStackRequestActionType} this processor is designed for
     */
    ItemStackRequestActionType getType();

    /**
     * Returns a default error response for failed actions.
     *
     * @return the predefined {@link #ERROR_RESPONSE} instance
     */
    default ActionResponse error() {
        return ERROR_RESPONSE;
    }

    /**
     * Validates whether the client's stack network ID matches the expected server ID.
     * <p>
     * This method is used to ensure data integrity between client and server. If the client's
     * stack network ID is less than 0, it indicates the client is deferring to the server's
     * data, typically in multi-action requests where the first action validates the ID.
     *
     * @param expectedSNID the expected stack network ID from the server
     * @param clientSNID   the stack network ID provided by the client
     *
     * @return {@code true} if validation fails, {@code false} if validation passes
     */
    default boolean failToValidateStackNetworkId(int expectedSNID, int clientSNID) {
        // If the client's stackNetworkId is less than 0, it indicates that the client ensures data integrity and requests adherence to the server's data.
        // This usually happens when an ItemStackRequest contains multiple actions with the same source/destination container.
        // The first action checks the id, so subsequent actions do not need to repeat the check.
        return clientSNID > 0 && expectedSNID != clientSNID;
    }
}
