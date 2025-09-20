package org.allaymc.api.world.sound;

import org.allaymc.api.item.type.ItemType;

/**
 * Sound played when a player fast-equips an item.
 *
 * @param itemType the item type that was equipped. The exact sound may depend on the item type
 */
public record EquipItemSound(ItemType<?> itemType) implements Sound {
}
