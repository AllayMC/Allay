package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;

import java.util.List;

/**
 * @author daoge_cmd
 */
public record ActionResponse(
        boolean ok,
        List<ItemStackResponseContainer> containers
) {
}
