package org.allaymc.server.container.processor;

import org.allaymc.api.container.Container;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.allaymc.server.entity.component.EntityPlayerContainerHolderComponentImpl;
import org.allaymc.server.entity.component.EntityPlayerContainerViewerComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
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
     * Validates whether the client's stack unique ID matches the expected server ID.
     * <p>
     * This method is used to ensure data integrity between client and server. If the client's
     * stack unique ID is less than 0, it indicates the client is deferring to the server's
     * data, typically in multi-action requests where the first action validates the ID.
     *
     * @param expectedId the expected stack unique ID from the server
     * @param clientId   the stack unique ID provided by the client
     * @return {@code true} if validation fails, {@code false} if validation passes
     */
    default boolean failToValidateStackUniqueId(int expectedId, int clientId) {
        // If the client's stackNetworkId is less than 0, it indicates that the client ensures data integrity and requests adherence to the server's data.
        // This usually happens when an ItemStackRequest contains multiple actions with the same source/destination container.
        // The first action checks the id, so subsequent actions do not need to repeat the check.
        return clientId > 0 && expectedId != clientId;
    }

    /**
     * Returns the reachable container for the given container name. This includes opened containers and
     * containers that the player holds.
     */
    @SuppressWarnings("unchecked")
    static <T extends Container> T getContainerFrom(EntityPlayer player, FullContainerName containerName) {
        var playerImpl = (EntityPlayerImpl) player;
        var container = ((EntityPlayerContainerViewerComponentImpl) playerImpl.getContainerViewerComponent()).getOpenedContainer(containerName.getContainer());
        if (container == null) {
            container = ((EntityPlayerContainerHolderComponentImpl) playerImpl.getContainerHolderComponent()).getContainer(containerName.getContainer());
        }
        return (T) container;
    }

    /**
     * Gets slot type of slot in a container.
     */
    static ContainerSlotType getSlotType(Container container, int slot) {
        return ContainerNetworkInfo.getInfo(container.getContainerType()).getSlotType(slot);
    }

    /**
     * Maps the slot in a container to network slot index.
     */
    static int toNetworkSlotIndex(Container container, int index) {
        return ContainerNetworkInfo.getInfo(container.getContainerType()).networkSlotIndexMapper().inverse().get(index);
    }

    /**
     * Maps the slot in a container from network slot index.
     */
    static int fromNetworkSlotIndex(Container container, int index) {
        return ContainerNetworkInfo.getInfo(container.getContainerType()).networkSlotIndexMapper().get(index);
    }
}
