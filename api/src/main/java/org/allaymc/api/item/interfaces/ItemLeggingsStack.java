package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;

/**
 * {@code ItemLeggingsStack} represents leggings with wearable-slot and armor-trim
 * capabilities. The inherited item contract supplies stack state and item
 * interactions, while {@code ItemWearableComponent} identifies the equipment
 * destination and {@code ItemTrimmableComponent} exposes the trim carried by
 * the item.
 */
public interface ItemLeggingsStack extends ItemStack, ItemWearableComponent, ItemTrimmableComponent {
}
