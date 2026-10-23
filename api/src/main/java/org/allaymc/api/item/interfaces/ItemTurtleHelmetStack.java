package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;

/**
 * {@code ItemTurtleHelmetStack} represents a turtle helmet stack with wearable-slot and
 * repair capabilities. The inherited item contract supplies ordinary stack
 * state and damage operations, while the component contracts identify its
 * equipment destination and check whether another item type can repair it.
 */
public interface ItemTurtleHelmetStack extends ItemStack, ItemWearableComponent, ItemRepairableComponent {
}
