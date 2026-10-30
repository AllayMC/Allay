package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;

import java.util.List;

/**
 * {@code ActionResponse} represents an action's success status and the container-slot
 * changes to include in the request response. Request processing stops later actions
 * when {@code ok} is {@code false}; successful entries are later coalesced by network
 * category and slot.
 *
 * @param ok whether request processing may continue after this action
 * @param containers The supplied list remains shared with the response; later mutations
 *     affect request aggregation and its reported container entries.
 *
 * @author daoge_cmd
 */
public record ActionResponse(
        boolean ok,
        List<ItemStackResponseContainer> containers
) {
}
