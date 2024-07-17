package org.allaymc.server.loottable.context;

import lombok.AllArgsConstructor;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class BreakBlockContext implements Context {
    protected Entity entity;
    protected ItemStack usedItem;
}
