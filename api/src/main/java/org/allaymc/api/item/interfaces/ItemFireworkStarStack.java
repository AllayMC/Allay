package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemFireworkStarBaseComponent;

/**
 * {@code ItemFireworkStarStack} represents a firework star stack with
 * specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemFireworkStarBaseComponent} supplies access to the single stored
 * firework explosion definition.
 */
public interface ItemFireworkStarStack extends ItemStack, ItemFireworkStarBaseComponent {
}
