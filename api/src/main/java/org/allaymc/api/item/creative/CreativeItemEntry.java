package org.allaymc.api.item.creative;

import org.allaymc.api.item.ItemStack;

/**
 * {@code CreativeItemEntry} associates a creative item stack with its group and
 * registry-wide entry index. The index is zero-based on the server; protocol
 * encoding adds one for the creative entry's positive network ID. The record
 * retains the supplied group and stack references.
 *
 * @param index zero-based registry entry index
 * @param group group owning this creative entry
 * @param itemStack registered mutable item stack reference
 *
 * @author daoge_cmd
 */
public record CreativeItemEntry(int index, CreativeItemGroup group, ItemStack itemStack) {
}
