package org.allaymc.api.item.interfaces.candlecake;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeCandleCakeStack extends ItemStack {
  ItemType<ItemLimeCandleCakeStack> LIME_CANDLE_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemLimeCandleCakeStack.class)
          .vanillaItem(VanillaItemId.LIME_CANDLE_CAKE)
          .build();
}
