package org.allaymc.api.item.interfaces.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCandleStack extends ItemStack {
    ItemType<ItemCandleStack> CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemCandleStack.class)
            .vanillaItem(VanillaItemId.CANDLE)
            .build();
}
