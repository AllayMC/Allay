package org.allaymc.api.item.data;

import org.allaymc.api.item.type.ItemType;

/**
 * @author daoge_cmd
 */
public record TrimPattern(ItemType<?> itemType, String patternId) {
}
