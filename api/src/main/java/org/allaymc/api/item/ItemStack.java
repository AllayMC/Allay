package org.allaymc.api.item;

import org.allaymc.api.item.component.ItemBaseComponent;

/**
 * {@code ItemStack} represents an item instance with a count and component-backed
 * state. Its base contract exposes identity, extra data, and gameplay operations;
 * specialized item interfaces add the capabilities supplied by their registered
 * item type's components.
 *
 * @author daoge_cmd
 */
public interface ItemStack extends ItemBaseComponent {
}
