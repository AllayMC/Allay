package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemToolComponent;

/**
 * {@code ItemAxeStack} represents an axe stack with tool classification and repair-material checks.
 * The inherited item contract supplies ordinary stack state and interactions,
 * while the tool marker and repair component let callers recognize axes and
 * check candidate repair materials through one typed item.
 */
public interface ItemAxeStack extends ItemStack, ItemToolComponent, ItemRepairableComponent {
}
