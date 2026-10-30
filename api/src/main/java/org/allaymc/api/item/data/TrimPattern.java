package org.allaymc.api.item.data;

import org.allaymc.api.item.type.ItemType;

/**
 * {@code TrimPattern} describes an armor trim pattern and its smithing-template
 * item type. Trim persistence uses {@code patternId}, while protocol encoding
 * also exposes the template item's identifier. The record retains the supplied
 * values without resolving or validating either identifier.
 *
 * @param itemType smithing-template item type reference
 * @param patternId trim persistence pattern identifier
 *
 * @author daoge_cmd
 */
public record TrimPattern(ItemType<?> itemType, String patternId) {
}
