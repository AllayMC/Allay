package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.component.ItemDyeableComponent;

/**
 * {@code ItemLeatherHorseArmorStack} represents leather horse armor with a custom dye color.
 * The inherited horse armor contract supplies ordinary stack state and item
 * interactions, while {@code ItemDyeableComponent} exposes the operations for
 * inspecting, replacing, or clearing the stored color.
 */
public interface ItemLeatherHorseArmorStack extends ItemHorseArmorStack, ItemDyeableComponent {
}
