package org.allaymc.api.world.sound;

import org.allaymc.api.item.type.ItemType;

/**
 * {@code EquipItemSound} describes the sound of equipping elytra or an armor
 * item. The current runtime selects an armor-tier event and shares the diamond
 * sound with netherite; callers must supply a supported equipment type rather
 * than an arbitrary item.
 *
 * @param itemType supported armor or elytra type
 */
public record EquipItemSound(ItemType<?> itemType) implements Sound {
}
