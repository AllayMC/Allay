package org.allaymc.api.item.creative;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public record CreativeItemEntry(int index, CreativeItemGroup group, ItemStack itemStack) {
}
