package org.allaymc.api.item.descriptor;

import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public interface ItemDescriptor {
    boolean match(ItemStack itemStack);
}
