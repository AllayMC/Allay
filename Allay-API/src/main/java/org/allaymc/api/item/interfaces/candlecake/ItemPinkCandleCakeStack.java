package org.allaymc.api.item.interfaces.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCandleCakeStack extends ItemStack {
  ItemType<ItemPinkCandleCakeStack> PINK_CANDLE_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemPinkCandleCakeStack.class)
          .vanillaItem(VanillaItemId.PINK_CANDLE_CAKE)
          .build();
}
