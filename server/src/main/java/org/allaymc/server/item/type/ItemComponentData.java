package org.allaymc.server.item.type;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

/**
 * @author daoge_cmd
 */
public record ItemComponentData(boolean componentBased, NbtMap components, ItemVersion version) {
    public static final ItemComponentData DEFAULT = new ItemComponentData(false, NbtMap.EMPTY, ItemVersion.NONE);
}
