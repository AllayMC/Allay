package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemToolComponent;

/**
 * {@code ItemHoeStack} represents a hoe stack with tool classification and repair-material
 * checks. The inherited item contract supplies ordinary stack state and
 * interactions, while the additional component contracts identify the stack as
 * a tool and check whether another item type can repair it.
 */
public interface ItemHoeStack extends ItemStack, ItemToolComponent, ItemRepairableComponent {
}
