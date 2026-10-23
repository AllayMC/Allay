package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;

/**
 * {@code ItemBowStack} represents a bow stack with repair-material checks.
 * The inherited item contract supplies use, damage, enchantment, and stack
 * operations; {@code ItemRepairableComponent} adds the material compatibility
 * query needed when callers decide whether another item can repair this bow.
 */
public interface ItemBowStack extends ItemStack, ItemRepairableComponent {
}
