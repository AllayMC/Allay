package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemSignBaseComponent;

/**
 * {@code ItemSignStack} represents a sign stack with specialized behavior
 * exposed through the item API. The inherited {@link ItemStack} contract
 * supplies ordinary stack operations, while {@link ItemSignBaseComponent}
 * exposes the standing and wall sign block variants used for placement.
 */
public interface ItemSignStack extends ItemStack, ItemSignBaseComponent {
}
