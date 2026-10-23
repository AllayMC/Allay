package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.component.ItemDyeableComponent;

/**
 * {@code ItemLeatherHelmetStack} represents a leather helmet with a custom dye color.
 * The inherited helmet contract supplies wearable, trimming, and repair
 * operations, while {@code ItemDyeableComponent} lets callers inspect, replace,
 * or clear the color stored by this item.
 */
public interface ItemLeatherHelmetStack extends ItemHelmetStack, ItemDyeableComponent {
}
