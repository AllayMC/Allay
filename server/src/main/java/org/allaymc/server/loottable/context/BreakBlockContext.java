package org.allaymc.server.loottable.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.loottable.context.Context;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class BreakBlockContext implements Context {
    protected Entity entity;
    protected ItemStack usedItem;
}
