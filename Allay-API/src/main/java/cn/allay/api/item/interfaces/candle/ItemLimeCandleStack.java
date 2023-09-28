package cn.allay.api.item.interfaces.candle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeCandleStack extends ItemStack {
  ItemType<ItemLimeCandleStack> LIME_CANDLE_TYPE = ItemTypeBuilder
          .builder(ItemLimeCandleStack.class)
          .vanillaItem(VanillaItemId.LIME_CANDLE)
          .build();
}
