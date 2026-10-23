package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemFireworkRocketBaseComponent;

/**
 * {@code ItemFireworkRocketStack} represents a firework rocket stack with
 * specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemFireworkRocketBaseComponent} supplies flight duration and
 * explosion configuration for the launched rocket.
 */
public interface ItemFireworkRocketStack extends ItemStack, ItemFireworkRocketBaseComponent {
}
