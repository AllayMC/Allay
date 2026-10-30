package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.component.ItemWearableComponent;

/**
 * {@code ItemHelmetStack} represents a helmet stack with wearable, trimming, and repair
 * capabilities. The inherited item contract supplies stack state, while the
 * component contracts identify the armor destination, expose trim data, and
 * check which materials can repair the helmet.
 */
public interface ItemHelmetStack extends ItemStack, ItemWearableComponent, ItemTrimmableComponent, ItemRepairableComponent {
}
