package org.allaymc.api.item.interfaces.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCandleStack extends ItemStack {
  ItemType<ItemPinkCandleStack> PINK_CANDLE_TYPE = ItemTypeBuilder
          .builder(ItemPinkCandleStack.class)
          .vanillaItem(VanillaItemId.PINK_CANDLE)
          .build();
}
