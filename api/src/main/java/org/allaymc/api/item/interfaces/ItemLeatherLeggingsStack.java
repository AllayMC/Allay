package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.component.ItemDyeableComponent;

/**
 * {@code ItemLeatherLeggingsStack} represents leather leggings that combine the leggings
 * contract with a custom dye color. The inherited leggings contract supplies
 * wearable-slot and trim operations, while {@code ItemDyeableComponent} lets
 * callers inspect, replace, or clear this item's custom color.
 */
public interface ItemLeatherLeggingsStack extends ItemLeggingsStack, ItemDyeableComponent {
}
