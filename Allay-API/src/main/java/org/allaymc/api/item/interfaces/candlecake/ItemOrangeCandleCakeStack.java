package org.allaymc.api.item.interfaces.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeCandleCakeStack extends ItemStack {
  ItemType<ItemOrangeCandleCakeStack> ORANGE_CANDLE_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemOrangeCandleCakeStack.class)
          .vanillaItem(VanillaItemId.ORANGE_CANDLE_CAKE)
          .build();
}
