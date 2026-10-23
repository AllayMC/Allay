package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemShieldBaseComponent;

/**
 * {@code ItemShieldStack} represents a shield stack with blocking and repair-material
 * capabilities. The inherited item contract supplies stack state and damage
 * operations, while {@code ItemShieldBaseComponent} exposes block eligibility,
 * durability loss, and attacker checks used by player damage handling.
 */
public interface ItemShieldStack extends ItemStack, ItemShieldBaseComponent, ItemRepairableComponent {
}
