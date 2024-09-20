package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;

import static org.allaymc.api.item.type.ItemTypes.AIR;

public interface ItemAirStack extends ItemStack {
    // We can provide a null info for air type
    // And only for air type we can do that
    ItemStack AIR_STACK = AIR.createItemStack(null);
}
