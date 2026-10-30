package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;

/**
 * {@code ItemElytraStack} represents an elytra stack with wearable-slot and repair
 * capabilities. The inherited item contract supplies stack state and damage
 * operations, while the component contracts identify its equipment destination
 * and check whether another item type is a compatible repair material.
 */
public interface ItemElytraStack extends ItemStack, ItemWearableComponent, ItemRepairableComponent {
}
