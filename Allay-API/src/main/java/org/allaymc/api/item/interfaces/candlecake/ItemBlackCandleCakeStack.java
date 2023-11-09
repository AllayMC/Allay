package org.allaymc.api.item.interfaces.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackCandleCakeStack extends ItemStack {
  ItemType<ItemBlackCandleCakeStack> BLACK_CANDLE_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemBlackCandleCakeStack.class)
          .vanillaItem(VanillaItemId.BLACK_CANDLE_CAKE)
          .build();
}
