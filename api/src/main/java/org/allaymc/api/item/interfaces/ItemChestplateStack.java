package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;

/**
 * {@code ItemChestplateStack} represents a chestplate stack with wearable, trimming, and
 * repair capabilities. The inherited item contract supplies stack state, while
 * the component contracts identify the armor destination, expose trim data, and
 * check materials used to repair the chestplate.
 */
public interface ItemChestplateStack extends ItemStack, ItemWearableComponent, ItemTrimmableComponent, ItemRepairableComponent {
}
