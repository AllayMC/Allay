package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemEdibleComponent;

/**
 * {@code ItemEnchantedGoldenAppleStack} represents an enchanted golden apple
 * stack with specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemEdibleComponent} supplies eating eligibility, eating time,
 * nutrition values, drink behavior, and consumption callbacks.
 */
public interface ItemEnchantedGoldenAppleStack extends ItemStack, ItemEdibleComponent {
}
