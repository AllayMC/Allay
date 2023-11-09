package org.allaymc.api.item.interfaces.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCandleCakeStack extends ItemStack {
  ItemType<ItemRedCandleCakeStack> RED_CANDLE_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemRedCandleCakeStack.class)
          .vanillaItem(VanillaItemId.RED_CANDLE_CAKE)
          .build();
}
