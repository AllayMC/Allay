package org.allaymc.server.item.type;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

/**
 * Item definition is used to be sent to the client to let the client know the attributes of the
 * item, such as the texture, the display name so that the client knows how to handle the item
 *
 * @author daoge_cmd
 */
public record ItemDefinition(boolean componentBased, NbtMap components, ItemVersion version) {
    public static final ItemDefinition DEFAULT = new ItemDefinition(false, NbtMap.EMPTY, ItemVersion.NONE);
}
