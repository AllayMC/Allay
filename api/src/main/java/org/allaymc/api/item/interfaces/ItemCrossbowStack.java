package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemCrossbowBaseComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;

/**
 * {@code ItemCrossbowStack} represents a crossbow stack with loaded-projectile and repair
 * capabilities. The inherited item contract supplies stack state and use
 * operations; {@code ItemCrossbowBaseComponent} exposes the retained load, and
 * {@code ItemRepairableComponent} checks whether a material can repair this item.
 */
public interface ItemCrossbowStack extends ItemStack, ItemCrossbowBaseComponent, ItemRepairableComponent {
}
