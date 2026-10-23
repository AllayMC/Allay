package org.allaymc.api.item.data;

import org.allaymc.api.item.type.ItemType;

/**
 * {@code TrimMaterial} describes an armor trim material, its ingredient item type,
 * and display color code. Trim persistence uses {@code materialId}, while protocol
 * encoding also exposes the ingredient's identifier and {@code color}; the record
 * retains these supplied values without validation.
 *
 * @param itemType ingredient item type reference
 * @param materialId trim persistence material identifier
 * @param color display color formatting code
 *
 * @author daoge_cmd
 */
public record TrimMaterial(ItemType<?> itemType, String materialId, String color) {
}
