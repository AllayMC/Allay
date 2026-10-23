package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * {@code ItemAirStack} represents the air item used to denote empty stack
 * results and container slots. The inherited {@link ItemStack} contract
 * supplies stack operations, while {@link #AIR_STACK} provides the shared
 * instance recognized by container empty-slot checks.
 */
public interface ItemAirStack extends ItemStack {
    /**
     * {@code AIR_STACK} holds the shared air stack used for empty container
     * slots. The air component supplies its own initialization state, so this
     * creation accepts {@code null}.
     */
    ItemStack AIR_STACK = AIR.createItemStack(null);
}
