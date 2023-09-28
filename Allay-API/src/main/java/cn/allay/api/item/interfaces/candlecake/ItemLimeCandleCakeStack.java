package cn.allay.api.item.interfaces.candlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

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
