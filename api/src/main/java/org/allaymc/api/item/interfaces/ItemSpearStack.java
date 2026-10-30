package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;

/**
 * {@code ItemSpearStack} represents a spear stack with repair-material checks. The
 * inherited item contract supplies stack state, attack, damage, and interaction
 * operations, while {@code ItemRepairableComponent} adds the material
 * compatibility query used to decide whether another item can repair the spear.
 */
public interface ItemSpearStack extends ItemStack, ItemRepairableComponent {
}
