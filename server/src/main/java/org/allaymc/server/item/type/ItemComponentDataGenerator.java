package org.allaymc.server.item.type;

import org.allaymc.api.item.type.ItemType;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface ItemComponentDataGenerator {
    ItemComponentData generate(ItemType<?> itemType);
}
