package org.allaymc.server.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;

import java.util.List;

/**
 * Allay Project 2023/11/4
 *
 * @author daoge_cmd
 */
public record ActionResponse(
        boolean ok,
        List<ItemStackResponseContainer> containers
) {
}
