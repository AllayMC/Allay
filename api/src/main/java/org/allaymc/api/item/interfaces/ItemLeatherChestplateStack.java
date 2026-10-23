package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.component.ItemDyeableComponent;

/**
 * {@code ItemLeatherChestplateStack} represents a leather chestplate with a custom dye
 * color. The inherited chestplate contract supplies wearable, trimming, and
 * repair operations, while {@code ItemDyeableComponent} lets callers inspect,
 * replace, or clear the color stored by this item.
 */
public interface ItemLeatherChestplateStack extends ItemChestplateStack, ItemDyeableComponent {
}
