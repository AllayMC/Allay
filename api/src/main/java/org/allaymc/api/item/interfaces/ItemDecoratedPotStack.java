package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemDecoratedPotBaseComponent;

/**
 * {@code ItemDecoratedPotStack} represents a decorated pot stack with
 * specialized behavior exposed through the item API. The inherited
 * {@link ItemStack} contract supplies ordinary stack operations, while
 * {@link ItemDecoratedPotBaseComponent} exposes the sherd layout stored on the
 * decorated pot.
 */
public interface ItemDecoratedPotStack extends ItemStack, ItemDecoratedPotBaseComponent {
}
