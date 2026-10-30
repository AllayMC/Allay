package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.component.ItemDyeableComponent;

/**
 * {@code ItemLeatherBootsStack} represents leather boots that combine the boots contract
 * with a custom dye color. The inherited boots contract exposes wearable-slot
 * and trim operations, while {@code ItemDyeableComponent} lets callers inspect,
 * replace, or clear the item's custom color.
 */
public interface ItemLeatherBootsStack extends ItemBootsStack, ItemDyeableComponent {
}
