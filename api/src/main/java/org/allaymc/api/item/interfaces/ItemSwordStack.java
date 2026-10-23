package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemToolComponent;

/**
 * {@code ItemSwordStack} represents a sword stack with tool classification and
 * repair-material checks. The inherited item contract supplies ordinary stack
 * state, attacks, and interactions, while the additional components identify
 * the item as a tool and check compatible materials for repair.
 */
public interface ItemSwordStack extends ItemStack, ItemToolComponent, ItemRepairableComponent {
}
